package com.intersys.bolt;

import com.intersys.bean.*;
import com.intersys.logic.*;
import com.intersys.topology.IngestionTopology;
import com.intersys.util.IIngestionLogger;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.IRichBolt;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;

import java.util.Map; 

public class RecordParserBolt implements IRichBolt, IRecordParserBolt {

	private static final long serialVersionUID = 1L;
	private OutputCollector collector;
	private Map<String, Object> config;
    private static final IIngestionLogger log = IngestionTopology.getLogger();
	private RecordParserBoltLogic logic = new RecordParserBoltLogic();
	private Tuple anchor = null;
	private int taskId;	

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
    public void prepare(Map config, TopologyContext context, OutputCollector collector) {
        this.collector = collector;
        this.config = config;
         try { taskId = context.getThisTaskId(); }
        catch (Throwable t) { taskId = 1; }
        logic.prepare(config,context,this);		
    }

	/*
	 *  Emits an instance of ParseError to stream Errors
	 */
	public void emitToErrors(ParseError parseError) {
		Values values = parseError.asValues();
		collector.emit("Errors",anchor,values);
	}


	/*
	 *  Emits an instance of ParseError to stream Errors
	 */
	public void emitToErrorsWithoutAnchor(ParseError parseError) {
		Values values = parseError.asValues();
		collector.emit("Errors",values);
	}

	/*
	 *  Emits an instance of Account to stream Accounts
	 */
	public void emitToAccounts(Account account) {
		Values values = account.asValues();
		collector.emit("Accounts",anchor,values);
	}


	/*
	 *  Emits an instance of Account to stream Accounts
	 */
	public void emitToAccountsWithoutAnchor(Account account) {
		Values values = account.asValues();
		collector.emit("Accounts",values);
	}

	/*
	 *  Emits an instance of Event to stream Events
	 */
	public void emitToEvents(Event event) {
		Values values = event.asValues();
		collector.emit("Events",anchor,values);
	}


	/*
	 *  Emits an instance of Event to stream Events
	 */
	public void emitToEventsWithoutAnchor(Event event) {
		Values values = event.asValues();
		collector.emit("Events",values);
	}

    @Override
    public void execute(Tuple tuple) {
    	anchor = tuple;
	    try {
	    	if ("RawRecords".equals(tuple.getSourceStreamId())) {
	    		RawRecord rawRecord = new RawRecord(tuple);
	            logic.readFromRawRecords(rawRecord, this);
	    	}
		} catch (Exception e) { 
			log.severe("RecordParserBolt", "execute", "Error executing Tuple", e); 
	    }
	}
	
    @Override
    /*
    *  NOTE: This method is not guaranteed to get called! Do not depend on it!
    */
    public void cleanup() {
		logic.cleanup(this);
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declareStream("Errors", ParseError.asFields());
        declarer.declareStream("Accounts", Account.asFields());
        declarer.declareStream("Events", Event.asFields());
    }
    
    public void ack() {
    	collector.ack(anchor);
    }
    
    public void fail() {
    	collector.fail(anchor);
    }

	@Override
	public Map<String, Object> getComponentConfiguration() {
		return config;
	}
	
	@Override
	public int getTaskId() {
		return taskId;
	}

}
