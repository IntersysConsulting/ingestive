package com.intersys.bean;

// Begin imports 

import static org.junit.Assert.fail;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.codehaus.jettison.json.JSONObject;
import org.junit.Test;

// End imports 

public class RawRecordTest {

	@Test
	public void testJsonification() {
		try {
			new RawRecord(new JSONObject(RawRecord.sample().toJsonString()));
		} catch (Exception e) {
			fail("Failed JSONification");
		}
	}
	
	@Test
	public void testTupilization() {
		RawRecord.sample().asValues();
	}

	@Test
	public void testSerialization() {
		
		try {

			serDeser(RawRecord.sample());
			serDeser(new RawRecord());
			
		} catch (Throwable t) {
			fail("Object not serializable: "+t);
		}

	}

	private RawRecord serDeser(RawRecord obj) throws Throwable {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(baos);
		oos.writeObject(obj);
		oos.close();
		ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(baos.toByteArray()));
		return (RawRecord) ois.readObject();
	}
	
	// Begin custom methods
	
	
	// End custom methods

	
}
