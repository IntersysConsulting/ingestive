package com.intersys.data;

// Begin imports 

import static org.junit.Assert.fail;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.codehaus.jettison.json.JSONObject;
import org.junit.Test;

import com.intersys.data.RecordParser;
import com.intersys.exception.GoodRecordException;
import com.intersys.exception.ParseException;

// End imports 

public class RecordParserTest {

	@Test
	public void testGoodRecord() {

		try {
			String record = "123456 8 31241002";
			RecordParser rp = new RecordParser();
			rp.parse(record);
		} catch (ParseException e) {
			fail("Failed to recognize good record");
		} catch (GoodRecordException e) {
			return;
		}

		fail("Failed to throw ParseException or GoodRecordException");

	}

	@Test
	public void testBadRecord() {

		try {
			String record = "123456 X 31241002";
			RecordParser rp = new RecordParser();
			rp.parse(record);
		} catch (ParseException e) {
			return;
		} catch (GoodRecordException e) {
			fail("Failed to recognize bad record");
		}

		fail("Failed to throw ParseException or GoodRecordException");

	}

	
}
