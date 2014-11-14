package com.intersys.accounts;

// Begin imports 

import static org.junit.Assert.fail;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.codehaus.jettison.json.JSONObject;
import org.junit.Test;

// End imports 

public class DeDuperTest {

	@Test
	public void testAllUnique() {
		try {
			DeDuper deduper = new DeDuper();
			
			if (!deduper.isNewAccountId("a")) {
				fail("Incorrectly identified new account");
			}
			
			if (!deduper.isNewAccountId("b")) {
				fail("Incorrectly identified new account");
			}
			
			if (!deduper.isNewAccountId("c")) {
				fail("Incorrectly identified new account");
			}

		} catch (Exception e) {
			fail("Failed isNewAccountId()");
		}
	}

	@Test
	public void testSomeUnique() {
		try {
			DeDuper deduper = new DeDuper();
			
			if (!deduper.isNewAccountId("a")) {
				fail("Incorrectly identified new account");
			}
			
			if (!deduper.isNewAccountId("b")) {
				fail("Incorrectly identified new account");
			}
			
			if (deduper.isNewAccountId("a")) {
				fail("Incorrectly identified new account");
			}

		} catch (Exception e) {
			fail("Failed isNewAccountId()");
		}
	}

	
}
