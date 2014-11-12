mvn -e exec:java -Dexec.classpathScope=compile -Dexec.mainClass=com.intersys.topology.IngestionTopology -Djava.util.logging.config.file=src/main/resources/logging.properties

#To execute in production
#storm jar ingestion-storm-1.0.0-SNAPSHOT-jar-with-dependencies.jar com.intersys.topology.IngestionTopology prod -Djava.util.logging.config.file=prod.logging.properties

