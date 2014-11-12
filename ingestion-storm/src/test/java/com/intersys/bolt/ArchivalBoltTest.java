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

public class ArchivalBoltTest  {
	
		// Begin declarations 

		// End declarations 

	@Test
	public void testSerialization() {
		
		try {
			new ObjectOutputStream(new ByteArrayOutputStream()).writeObject(new ArchivalBolt() );
		} catch (Throwable t) {
			fail("Class ArchivalBoltLogic is not serializable");
		}

	}

	@Test
	public void testReadFromArchiveCopies() {

		// Begin testReadFromArchiveCopies logic 

//		HashMap<String,Object> config = new HashMap<String,Object>();
//		MockedBoltOutputCollector collector = new MockedBoltOutputCollector();
//		
//		MockedTuple tuple = new MockedTuple("ArchiveCopies", RawRecord.sample().asValues()); 
//		ArchivalBolt bolt = new ArchivalBolt();

//		bolt.prepare(config,null,new OutputCollector(collector));
//		bolt.execute(tuple);
//		bolt.cleanup()

			// Validate execution side effects by interrogating collector
					
		// End testReadFromArchiveCopies logic 

	}

// Begin custom methods 


// End custom methods 

}
