package com.intersys.bolt;

import backtype.storm.tuple.Tuple;

import com.intersys.bean.*;

public interface INewAccountProcessorBolt {

    public void ack();

    public void fail();

	public int getTaskId();

}