package com.intersys.mock;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.intersys.bean.*;

import backtype.storm.task.IOutputCollector;
import backtype.storm.tuple.Tuple;

public class MockedBoltOutputCollector implements IOutputCollector {

	public boolean acked = false;
	public boolean failed = false;
	public Throwable error = null;

	public List<ParseError> emittedToErrors = new ArrayList<ParseError>();
	public List<Account> emittedToAccounts = new ArrayList<Account>();
	public List<Event> emittedToEvents = new ArrayList<Event>();
	public List<Account> emittedToNewAccounts = new ArrayList<Account>();

	public List<Object> others = new ArrayList<Object>();
	
	public MockedBoltOutputCollector() {

	}

	@Override
	public List<Integer> emit(String streamId, Collection<Tuple> anchors, List<Object> tuple) {
		
		List<Integer> result = new ArrayList<Integer>();
		
		if (streamId == null) { return result; }
		
		if (streamId.equals("Errors")) {
			emittedToErrors.add(new ParseError(new MockedTuple(streamId, tuple)));
			return result;
		}
		if (streamId.equals("Accounts")) {
			emittedToAccounts.add(new Account(new MockedTuple(streamId, tuple)));
			return result;
		}
		if (streamId.equals("Events")) {
			emittedToEvents.add(new Event(new MockedTuple(streamId, tuple)));
			return result;
		}
		if (streamId.equals("NewAccounts")) {
			emittedToNewAccounts.add(new Account(new MockedTuple(streamId, tuple)));
			return result;
		}
		
		others.add(tuple);
		return result;
	}

	@Override
	public void emitDirect(int taskId, String streamId, Collection<Tuple> anchors, List<Object> tuple) {

	}

	@Override
	public void ack(Tuple input) {
		acked = true;
	}

	@Override
	public void fail(Tuple input) {
		failed = true;
	}

	@Override
	public void reportError(Throwable error) {
		this.error = error;
	}

}
