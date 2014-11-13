package com.intersys.spout;

import static org.junit.Assert.fail;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import org.junit.Test;

import backtype.storm.spout.SpoutOutputCollector;

import com.intersys.bean.*;
import com.intersys.mock.*;

public class KafkaReaderSpoutTest {
 
	@Test
	public void testSerialization() {
		
//		try {
//			new ObjectOutputStream(new ByteArrayOutputStream()).writeObject(new KafkaReaderSpout() );
//		} catch (Throwable t) {
//			fail("Class KafkaReaderSpout is not serializable");
//		}

	}

	@Test
	public void testNextTuple() {
		
//		KafkaReaderSpout spout = new KafkaReaderSpout();

//		MockedSpoutOutputCollector collector = new MockedSpoutOutputCollector();
//		HashMap<String,Object> config = new HashMap<String, Object>();
		
//		spout.open(config,null,new SpoutOutputCollector(collector));
//		spout.nextTuple();
//		spout.close();
		
		// Validate side effects of nextTuple() call by interrogating collector
		
	}

}
