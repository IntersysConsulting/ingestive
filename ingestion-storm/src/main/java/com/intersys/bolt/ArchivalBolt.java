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

public class ArchivalBolt implements IRichBolt, IArchivalBolt {

	private static final long serialVersionUID = 1L;
	private OutputCollector collector;
	private Map<String, Object> config;
    private static final IIngestionLogger log = IngestionTopology.getLogger();
	private ArchivalBoltLogic logic = new ArchivalBoltLogic();
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

    @Override
    public void execute(Tuple tuple) {
    	anchor = tuple;
	    try {
	    	if ("ArchiveCopies".equals(tuple.getSourceStreamId())) {
	    		RawRecord rawRecord = new RawRecord(tuple);
	            logic.readFromArchiveCopies(rawRecord, this);
	    	}
		} catch (Exception e) { 
			log.severe("ArchivalBolt", "execute", "Error executing Tuple", e); 
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
