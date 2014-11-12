package com.intersys.mock;

import java.util.ArrayList;
import java.util.List;

import com.intersys.bean.*;

import backtype.storm.spout.ISpoutOutputCollector;

public class MockedSpoutOutputCollector implements ISpoutOutputCollector {
	
	public List<RawRecord> emittedToRawRecords = new ArrayList<RawRecord>();
	public List<RawRecord> idsForRawRecords = new ArrayList<RawRecord>();
	public List<RawRecord> emittedToArchiveCopies = new ArrayList<RawRecord>();
	public List<RawRecord> idsForArchiveCopies = new ArrayList<RawRecord>();

	public List<Object> others = new ArrayList<Object>();

	public MockedSpoutOutputCollector() {
	}

	@Override
	public List<Integer> emit(String streamId, List<Object> tuple, Object messageId) {
		
		List<Integer> result = new ArrayList<Integer>();
		
		if (streamId == null) { return result; }
		
		if (streamId.equals("RawRecords")) {
			emittedToRawRecords.add(new RawRecord(new MockedTuple(streamId, tuple)));
			if (messageId == null) {
				messageId = RawRecord.sample();
			}
			idsForRawRecords.add((RawRecord)messageId);
			return result;
		}
		if (streamId.equals("ArchiveCopies")) {
			emittedToArchiveCopies.add(new RawRecord(new MockedTuple(streamId, tuple)));
			if (messageId == null) {
				messageId = RawRecord.sample();
			}
			idsForArchiveCopies.add((RawRecord)messageId);
			return result;
		}
		
		others.add(tuple);
		return result;
		
	}

	@Override
	public void emitDirect(int taskId, String streamId, List<Object> tuple,
			Object messageId) {  
	}

	@Override
	public void reportError(Throwable error) {
	}

}
