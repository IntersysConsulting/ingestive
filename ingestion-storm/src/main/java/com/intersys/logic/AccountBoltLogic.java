package com.intersys.logic;

	// Begin imports 

import java.io.Serializable;
import java.util.HashSet;
import java.util.Map;

import backtype.storm.task.TopologyContext;

import com.intersys.accounts.DeDuper;
import com.intersys.bean.Account;
import com.intersys.bolt.IAccountBolt;
import com.intersys.topology.IngestionTopology;
import com.intersys.util.IIngestionLogger;

	// End imports 

public class AccountBoltLogic implements Serializable {

	private static final long serialVersionUID = 1L;
		
	private DeDuper deDuper;
	
		// Begin declarations 

    private static final IIngestionLogger log = IngestionTopology.getLogger();
    
		// End declarations 
		
    public void readFromAccounts(Account account, IAccountBolt bolt) {

			// Begin readFromAccounts() logic 

    	String id = account.getId();
    	
    	if (deDuper.isNewAccountId(id)) {
    		bolt.emitToNewAccountsWithoutAnchor(account);
    	}

			// End readFromAccounts() logic 

    }

    public void prepare(Map conf, TopologyContext context, IAccountBolt bolt) {

			// Begin prepare() logic 

    	deDuper = new DeDuper();

			// End prepare() logic 

    }

	/*
    *  NOTE: This method is not guaranteed to get called! Do not depend on it!
	*/
    public void cleanup(IAccountBolt bolt) {

			// Begin cleanup() logic 


			// End cleanup() logic 

    }

// Begin custom methods 

	public void sleep(long msec) {
		try { Thread.sleep(msec); } catch (Throwable t) {  }
	}

// End custom methods 

}
