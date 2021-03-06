package com.intersys.logic;

	// Begin imports 

import com.intersys.bean.*;
import com.intersys.bolt.IEventProcessorBolt;
import com.intersys.kafka.IngestionProducer;
import com.intersys.topology.IngestionTopology;
import com.intersys.util.IngestionLogger;
import com.intersys.util.IIngestionLogger;

import backtype.storm.task.TopologyContext;
import backtype.storm.tuple.Tuple;

import java.io.Serializable;
import java.util.Map;

	// End imports 

public class EventProcessorBoltLogic implements Serializable {

	private static final long serialVersionUID = 1L;
		
		// Begin declarations 

    private static final IIngestionLogger log = IngestionTopology.getLogger();
	private IngestionProducer producer;

		// End declarations 
		
    public void readFromEvents(Event event, IEventProcessorBolt bolt) {

			// Begin readFromEvents() logic 

    	String message = "Event:  "+event.getJson();
		producer.send(message);

			// End readFromEvents() logic 

    }

    public void prepare(Map conf, TopologyContext context, IEventProcessorBolt bolt) {

			// Begin prepare() logic 

    	String csBrokerList = (String) conf.get("ingestion.kafka.brokers");
		producer = new IngestionProducer("boltLogs", csBrokerList);

			// End prepare() logic 

    }

	/*
    *  NOTE: This method is not guaranteed to get called! Do not depend on it!
	*/
    public void cleanup(IEventProcessorBolt bolt) {

			// Begin cleanup() logic 

    	producer.close();

			// End cleanup() logic 

    }

// Begin custom methods 

	public void sleep(long msec) {
		try { Thread.sleep(msec); } catch (Throwable t) {  }
	}

// End custom methods 

}
