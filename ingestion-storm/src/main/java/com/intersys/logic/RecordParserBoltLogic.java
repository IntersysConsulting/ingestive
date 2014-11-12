package com.intersys.logic;

	// Begin imports 

import java.io.Serializable;
import java.util.Map;

import backtype.storm.task.TopologyContext;

import com.intersys.bean.Account;
import com.intersys.bean.Event;
import com.intersys.bean.ParseError;
import com.intersys.bean.RawRecord;
import com.intersys.bolt.IRecordParserBolt;
import com.intersys.data.RecordParser;
import com.intersys.exception.GoodRecordException;
import com.intersys.exception.ParseException;
import com.intersys.topology.IngestionTopology;
import com.intersys.util.IIngestionLogger;

	// End imports 

public class RecordParserBoltLogic implements Serializable {

	private static final long serialVersionUID = 1L;
		
		// Begin declarations 

    private static final IIngestionLogger log = IngestionTopology.getLogger();

    private RecordParser parser;
    
		// End declarations 
		
    public void readFromRawRecords(RawRecord rawRecord, IRecordParserBolt bolt) {

			// Begin readFromRawRecords() logic 

		String record = rawRecord.getOriginalRecord();

		try {
		
			parser.parse(record);
		
		} catch (ParseException e) {
			
			ParseError parseError = new ParseError(record, e.getMessage());
			bolt.emitToErrorsWithoutAnchor(parseError);
		
		} catch (GoodRecordException e) {

			Account account = new Account(e.getAccount());
			bolt.emitToAccountsWithoutAnchor(account);
			
			Event event = new Event(e.getAccount(), e.getTime(), e.getType(), e.getJobj().toString());
			bolt.emitToEventsWithoutAnchor(event);
			
		}
    	
			// End readFromRawRecords() logic 

    }

    public void prepare(Map conf, TopologyContext context, IRecordParserBolt bolt) {

			// Begin prepare() logic 

        parser = new RecordParser();

			// End prepare() logic 

    }

	/*
    *  NOTE: This method is not guaranteed to get called! Do not depend on it!
	*/
    public void cleanup(IRecordParserBolt bolt) {

			// Begin cleanup() logic 


			// End cleanup() logic 

    }

// Begin custom methods 

	public void sleep(long msec) {
		try { Thread.sleep(msec); } catch (Throwable t) {  }
	}

// End custom methods 

}
