package com.intersys.mock;

import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.tuple.Fields;

public class MockedOutputFieldsDeclarer implements OutputFieldsDeclarer {

	public MockedOutputFieldsDeclarer() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void declare(Fields fields) {
		// TODO Auto-generated method stub

	}

	@Override
	public void declare(boolean direct, Fields fields) {
		// TODO Auto-generated method stub

	}

	@Override
	public void declareStream(String streamId, Fields fields) {
		// TODO Auto-generated method stub

	}

	@Override
	public void declareStream(String streamId, boolean direct, Fields fields) {
		// TODO Auto-generated method stub

	}

}
