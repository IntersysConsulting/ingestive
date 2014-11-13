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

public class ErrorLogBoltTest  {
	
		// Begin declarations 

		// End declarations 

	@Test
	public void testSerialization() {
		
//		try {
//			new ObjectOutputStream(new ByteArrayOutputStream()).writeObject(new ErrorLogBolt() );
//		} catch (Throwable t) {
//			fail("Class ErrorLogBoltLogic is not serializable");
//		}

	}

	@Test
	public void testReadFromErrors() {

		// Begin testReadFromErrors logic 

//		HashMap<String,Object> config = new HashMap<String,Object>();
//		MockedBoltOutputCollector collector = new MockedBoltOutputCollector();
//		
//		MockedTuple tuple = new MockedTuple("Errors", ParseError.sample().asValues()); 
//		ErrorLogBolt bolt = new ErrorLogBolt();

//		bolt.prepare(config,null,new OutputCollector(collector));
//		bolt.execute(tuple);
//		bolt.cleanup()

			// Validate execution side effects by interrogating collector
					
		// End testReadFromErrors logic 

	}

// Begin custom methods 


// End custom methods 

}
