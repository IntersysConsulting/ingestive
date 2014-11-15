ingestive
=========

A working Storm topology that reads events from and writes messages to a set of Kafka topics.  Illustrates a number of Kafka and Storm design patterns:

1) Formal Kafka tier that hides topic implementation and configuration details from collaborating components

2) Factoring of spout and bolt logic into three tiers (storm-specific, topology-specific and pure business logic) allowing for separation of concerns, simpler testing and fewer inter-developer dependencies.  Note that this especially applies to the spout reading from the Kafka topic.  This example does not use the KafkaSpout as that class follows many more anti-patterns than it does good patterns.

3) Stream beans that encapsulate knowledge about a storm stream: field names, types and order. 

Here is an visual representation of the topology.

![topology](https://github.com/IntersysConsulting/ingestive/blob/master/ingestion-storm/IngestionTopology.png) 
Records are read from a Kafka topic by the spout which writes them immediately to two streams, the main processing stream (RawRecords) and a stream for record archival (ArchiveCopies).  A parser bolt executes tuples (of shape RawRecord) from stream RawRecords, parses then and sends two different objects on two streams, one for new account identification (Accounts) and one to emulate further event processing (Events).  In the case of a parsing error, a tuple describing the error is written to stream Errors.   An account bolt filters out accounts that the bolt has seen before and emits tuples representing new accounts onto stream NewAccounts for subsequent processing by the New Account Processor bolt.  For demonstration purposes, the "leaf" bolts all write a log message to another Kafka topic.

The topology therefore reads records from one topic and writes records to another.  A stand-alone class (also included) writes records to the topology's input topic.  Another included class reads and writes records from the topology's output topic.

About the anotations above... The number after the bolt or spout name indicates the default parallelism hint (specificed in the configuration file).  The box overlaying a stream shows the stream name, the java type that describes the shape of the tuples on the stream and the individual field names declared on the stream.  The grouping for each stream is also shown.
