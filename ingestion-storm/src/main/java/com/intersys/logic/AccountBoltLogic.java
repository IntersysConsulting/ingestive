package com.intersys.logic;

	// Begin imports 

import java.io.Serializable;
import java.util.HashSet;
import java.util.Map;

import backtype.storm.task.TopologyContext;

import com.intersys.bean.Account;
import com.intersys.bolt.IAccountBolt;
import com.intersys.topology.IngestionTopology;
import com.intersys.util.IIngestionLogger;

	// End imports 

public class AccountBoltLogic implements Serializable {

	private static final long serialVersionUID = 1L;
		
		// Begin declarations 

    private static final IIngestionLogger log = IngestionTopology.getLogger();

    private HashSet<String> accounts;
    
		// End declarations 
		
    public void readFromAccounts(Account account, IAccountBolt bolt) {

			// Begin readFromAccounts() logic 

    	String id = account.getId();
    	if (!accounts.contains(id)) {
    		bolt.emitToNewAccountsWithoutAnchor(account);
    	}
    	accounts.add(id);

			// End readFromAccounts() logic 

    }

    public void prepare(Map conf, TopologyContext context, IAccountBolt bolt) {

			// Begin prepare() logic 

    	accounts = new HashSet<String>();

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
