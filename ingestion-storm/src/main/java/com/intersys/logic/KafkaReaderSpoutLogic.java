package com.intersys.logic;

	// Begin imports 

import com.intersys.bean.*;
import com.intersys.data.Generator;
import com.intersys.kafka.IngestionHighLevelConsumer;
import com.intersys.spout.IKafkaReaderSpout;
import com.intersys.util.IngestionLogger;
import com.intersys.util.IIngestionLogger;

import backtype.storm.task.TopologyContext;

import java.io.Serializable;
import java.util.Map;

	// End imports 

public class KafkaReaderSpoutLogic implements Serializable {

		// Begin declarations
		 
	private static final long serialVersionUID = 1L;

    private static final IIngestionLogger log = IngestionLogger.getInstance();
    private boolean written = false;
 
	private IngestionHighLevelConsumer consumer;

		// End declarations 

    public void nextTuple(final IKafkaReaderSpout spout) {

			// Begin nextTuple() logic 
			
        try {

        	if (consumer.hasNext()) {
            	
        		String record = consumer.next();
            	
            	RawRecord rawRecord = new RawRecord(record);
            	
            	spout.emitToRawRecords(rawRecord);
            	spout.emitToArchiveCopies(rawRecord);
        
        	}
        	
       		Thread.sleep(10);

    	} catch (InterruptedException e) {
    		log.finest("KafkaReaderSpoutLogic","nextTuple", e.toString());
        } catch (Exception e) {
       		log.severe("KafkaReaderSpoutLogic","nextTuple", e.toString());
        }

			// End nextTuple() logic 

    }

    public void open(Map map, TopologyContext topologyContext, IKafkaReaderSpout spout) {

			// Begin open() logic 
 
    	String zookeeperConnect = (String) map.get("ingestion.zookeeper.connect");
    	consumer = new IngestionHighLevelConsumer("rawRecords", "driver", zookeeperConnect);

			// End open() logic 

    }

    public void close(IKafkaReaderSpout spout) {

			// Begin close() logic 


			// End close() logic 

    }

    public void activate(IKafkaReaderSpout spout) {

			// Begin activate() logic 


			// End activate() logic 

    }

    public void deactivate(IKafkaReaderSpout spout) {

			// Begin deactivate() logic 


			// End deactivate() logic 

    }

    public void ack(Object o, IKafkaReaderSpout spout) {

			// Begin ack() logic 


			// End ack() logic 

    }

    public void fail(Object o, IKafkaReaderSpout spout) {

			// Begin fail() logic 


			// End fail() logic 

    }

// Begin custom methods 

// End custom methods 

}
