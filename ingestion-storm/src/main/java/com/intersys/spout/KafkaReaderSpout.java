package com.intersys.spout;

import com.intersys.bean.*;
import com.intersys.logic.*;
import com.intersys.util.*;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.IRichSpout;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;

import java.util.Map;  

import com.intersys.topology.IngestionTopology;  

public class KafkaReaderSpout implements IRichSpout, IKafkaReaderSpout {

	private static final long serialVersionUID = 1L;
	private static Map<String, Object> config = null;
    private static ThreadLocal<SpoutOutputCollector> collector = new ThreadLocal<SpoutOutputCollector>();
    private volatile static boolean activated = false; 
	private KafkaReaderSpoutLogic logic = new KafkaReaderSpoutLogic();
	private int taskId;	

    private static final IIngestionLogger log = IngestionTopology.getLogger();

    @Override
    public void nextTuple() {

        try {

			logic.nextTuple(this);

        } catch (Exception e) {
            log.severe("KafkaReaderSpout","nextTuple",e.toString());
        }
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
    public void open(Map config, TopologyContext topologyContext, SpoutOutputCollector collector) {
    	IngestionLogger.getInstance().configure(config);
        KafkaReaderSpout.collector.set(collector);
         try { taskId = topologyContext.getThisTaskId(); }
        catch (Throwable t) { taskId = 1; }
        logic.open(config,topologyContext,this);
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declareStream("RawRecords", RawRecord.asFields());
        declarer.declareStream("ArchiveCopies", RawRecord.asFields());
    }

    @Override
    public Map<String, Object> getComponentConfiguration() {
        return config;
    }

    /*
     * Unreliably emit an instance of RawRecord to stream RawRecords.  
     */
	public void emitToRawRecords(RawRecord rawRecord) {
		Values values = rawRecord.asValues();
		collector.get().emit("RawRecords",values);
	}

    /*
     * Reliably emit an instance of RawRecord to stream RawRecords.
     * The second parameter is to be used as a message ID for
     * notification of message ack or fail.  
     */
	public void emitToRawRecords(RawRecord rawRecord, RawRecord messageID) {
		Values values = rawRecord.asValues();
		collector.get().emit("RawRecords",values,messageID);
	}

    /*
     * Unreliably emit an instance of RawRecord to stream ArchiveCopies.  
     */
	public void emitToArchiveCopies(RawRecord rawRecord) {
		Values values = rawRecord.asValues();
		collector.get().emit("ArchiveCopies",values);
	}

    /*
     * Reliably emit an instance of RawRecord to stream ArchiveCopies.
     * The second parameter is to be used as a message ID for
     * notification of message ack or fail.  
     */
	public void emitToArchiveCopies(RawRecord rawRecord, RawRecord messageID) {
		Values values = rawRecord.asValues();
		collector.get().emit("ArchiveCopies",values,messageID);
	}

    @Override
    public void close() {
        activated = false;
        logic.close(this);
    }

    @Override
    public void ack(Object o) {
        logic.ack(o,this);
    }

    @Override
    public void fail(Object o) {
        logic.fail(o,this);
    }


    @Override
    public void activate() {
        if (!activated) {
            activated = true;
	        logic.activate(this);
        }
    }

    @Override
    public void deactivate() {
        activated = false;
        logic.deactivate(this);
    }
	
	@Override
	public int getTaskId() {
		return taskId;
	}

}
