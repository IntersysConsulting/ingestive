package com.intersys.bolt;

import backtype.storm.tuple.Tuple;

import com.intersys.bean.*;

public interface IAccountBolt {

	/*
	 *  Emits an instance of EarnedTagInfo to stream Tags
	 */
	public void emitToNewAccounts(Account account);
	
	/*
	 *  Emits an instance of EarnedTagInfo to stream Tags
	 */
	public void emitToNewAccountsWithoutAnchor(Account account);

    public void ack();

    public void fail();

	public int getTaskId();

}