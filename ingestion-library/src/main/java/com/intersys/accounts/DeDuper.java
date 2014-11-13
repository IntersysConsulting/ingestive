package com.intersys.accounts;

import java.util.HashSet;

public class DeDuper {

    private HashSet<String> accounts;

    public DeDuper() {

	}

    public boolean isNewAccountId(String id) {
    	
    	boolean result = !accounts.contains(id);
    	accounts.add(id);
    	return result;
    	
    }
    
}
