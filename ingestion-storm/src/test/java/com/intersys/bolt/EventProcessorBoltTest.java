package com.intersys.bolt;

	// Begin imports 

import static org.junit.Assert.fail;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import org.junit.Test;

import backtype.storm.task.OutputCollector;

import com.intersys.bean.*;
import com.intersys.mock.*;

	// End imports 

public class EventProcessorBoltTest  {
	
		// Begin declarations 

		// End declarations 

	@Test
	public void testSerialization() {
		
		try {
			new ObjectOutputStream(new ByteArrayOutputStream()).writeObject(new EventProcessorBolt() );
		} catch (Throwable t) {
			fail("Class EventProcessorBoltLogic is not serializable");
		}

	}

	@Test
	public void testReadFromEvents() {

		// Begin testReadFromEvents logic 

//		HashMap<String,Object> config = new HashMap<String,Object>();
//		MockedBoltOutputCollector collector = new MockedBoltOutputCollector();
//		
//		MockedTuple tuple = new MockedTuple("Events", Event.sample().asValues()); 
//		EventProcessorBolt bolt = new EventProcessorBolt();

//		bolt.prepare(config,null,new OutputCollector(collector));
//		bolt.execute(tuple);
//		bolt.cleanup()

			// Validate execution side effects by interrogating collector
					
		// End testReadFromEvents logic 

	}

// Begin custom methods 


// End custom methods 

}
