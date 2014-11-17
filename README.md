#ingestive

A working Storm topology that reads events from and writes messages to a set of Kafka topics.  Illustrates a number of Kafka and Storm design patterns:

1) Formal Kafka tier that hides topic implementation and configuration details from collaborating components

2) Factoring of spout and bolt logic into three tiers (storm-specific, topology-specific and pure business logic) allowing for separation of concerns, simpler testing and fewer inter-developer dependencies.  Note that this especially applies to the spout reading from the Kafka topic.  This example does not use the KafkaSpout as that class follows many more anti-patterns than it does good patterns.

3) Stream beans that encapsulate knowledge about a storm stream: field names, types and order. 

4) The notion of a Kafka topic as an independent component.  This component includes the producer and consumer that act as the component's public interfaces by hiding the topic congfiguration from users of the component.

Here is an visual representation of the topology.

![topology](https://github.com/IntersysConsulting/ingestive/blob/master/ingestion-storm/IngestionTopology.png) 
Records are read from a Kafka topic by the spout which writes them immediately to two streams, the main processing stream (RawRecords) and a stream for record archival (ArchiveCopies).  A parser bolt executes tuples (of shape RawRecord) from stream RawRecords, parses then and sends two different objects on two streams, one for new account identification (Accounts) and one to emulate further event processing (Events).  In the case of a parsing error, a tuple describing the error is written to stream Errors.   An account bolt filters out accounts that the bolt has seen before and emits tuples representing new accounts onto stream NewAccounts for subsequent processing by the New Account Processor bolt.  For demonstration purposes, the "leaf" bolts all write a log message to another Kafka topic.

The topology therefore reads records from one topic and writes records to another.  A stand-alone class (also included) writes records to the topology's input topic.  Another included class reads and writes records from the topology's output topic.

About the anotations above... The number after the bolt or spout name indicates the default parallelism hint (specificed in the configuration file).  The box overlaying a stream shows the stream name, the java type that describes the shape of the tuples on the stream and the individual field names declared on the stream.  The grouping for each stream is also shown.

###Build

To build the topology, pull all mavenized projects from this repo, change directory to the root directory of the ingestion project (/ingestion) and enter
```
    mvn install
```

###Run

Once the app is built ( look for ingestion-storm-1.0.0-SNAPSHOT-jar-with-dependencies.jar in /ingestion-storm/target/), you can invoke the three components:

#####RawRecordsWriter

RawRecordsWriter dumps 100K records onto the rawRecords topic and then terminates.
```
   java -cp ingestion-storm/target/ingestion-storm-1.0.0-SNAPSHOT-jar-with-dependencies.jar com.intersys.kafka.driver.RawRecordsWriter localhost:9092
```
where localhost:9092 is the host/port for one of your Kafka brokers

#####IngestionTopology

IngestionTopology configures the topology, submits the topology, waits a few minutes and then kills the topology.  The current configuration (see ingestion-storm/src/main/resources/dev.properties) sets topology.run.locally to true, resulting in execution in a LocalCluster.  The prod configuration properties file is set up to run on the server.
```
   java -cp ingestion-storm/target/ingestion-storm-1.0.0-SNAPSHOT-jar-with-dependencies.jar -Djava.util.logging.config.file=src/main/resources/logging.properties com.intersys.topology.IngestionTopology 
```

#####BoltLogsReader

BoltLogsReader reads however many log records are written to Kafka topic boltLogs by the Storm topology's "leaf" bolts.  There will be a handful more than twice as many log records read than there were raw records written.  

Note that this reader will wait for about 30 seconds before starting to read from the topic and once there are no more records on the topic the reader will terminate.  This means you probably want to start it soon after starting the other two components and not any sooner. 
```
   java -cp ingestion-storm/target/ingestion-storm-1.0.0-SNAPSHOT-jar-with-dependencies.jar com.intersys.kafka.driver.RawRecordsWriter localhost:2181
```
where localhost:2181 is the host/port for one of your ZooKeeper servers

