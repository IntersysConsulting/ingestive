package com.intersys.bolt;

import backtype.storm.tuple.Tuple;

import com.intersys.bean.*;

public interface IRecordParserBolt {

	/*
	 *  Emits an instance of EarnedTagInfo to stream Tags
	 */
	public void emitToErrors(ParseError parseError);
	
	/*
	 *  Emits an instance of EarnedTagInfo to stream Tags
	 */
	public void emitToErrorsWithoutAnchor(ParseError parseError);

	/*
	 *  Emits an instance of EarnedTagInfo to stream Tags
	 */
	public void emitToAccounts(Account account);
	
	/*
	 *  Emits an instance of EarnedTagInfo to stream Tags
	 */
	public void emitToAccountsWithoutAnchor(Account account);

	/*
	 *  Emits an instance of EarnedTagInfo to stream Tags
	 */
	public void emitToEvents(Event event);
	
	/*
	 *  Emits an instance of EarnedTagInfo to stream Tags
	 */
	public void emitToEventsWithoutAnchor(Event event);

    public void ack();

    public void fail();

	public int getTaskId();

}