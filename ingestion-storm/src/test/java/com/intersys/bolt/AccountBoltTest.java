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

public class AccountBoltTest  {
	
		// Begin declarations 

		// End declarations 

	@Test
	public void testSerialization() {
		
		try {
			new ObjectOutputStream(new ByteArrayOutputStream()).writeObject(new AccountBolt() );
		} catch (Throwable t) {
			fail("Class AccountBoltLogic is not serializable");
		}

	}

	@Test
	public void testReadFromAccounts() {

		// Begin testReadFromAccounts logic 

//		HashMap<String,Object> config = new HashMap<String,Object>();
//		MockedBoltOutputCollector collector = new MockedBoltOutputCollector();
//		
//		MockedTuple tuple = new MockedTuple("Accounts", Account.sample().asValues()); 
//		AccountBolt bolt = new AccountBolt();

//		bolt.prepare(config,null,new OutputCollector(collector));
//		bolt.execute(tuple);
//		bolt.cleanup()

			// Validate execution side effects by interrogating collector
					
		// End testReadFromAccounts logic 

	}

// Begin custom methods 


// End custom methods 

}
