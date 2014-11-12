package com.intersys.data;

public class Generator {

	private long nextTime = System.currentTimeMillis();
	
	private String[] account = { "124357" , "527184" , "209348" , "212201" , "670010" , "356223" , "880121" , "121009" };
	private int acct = 0;
	
	private long seed = System.currentTimeMillis();
	private long inc = 2203L;
	private long types = 5L;
	
	public Generator() {

	}

	public String nextRecord() {
		
		
		acct++;
		if (acct > account.length-1) { acct = 0; }

		seed = seed + inc;
		String type = String.valueOf(seed%types);
		
		if ((seed%31)==0) { 
			type = "X";
		}
	
		nextTime ++;
		long now = System.currentTimeMillis();
		if (now > nextTime) {
			nextTime = now;
		}
		String time = String.valueOf(nextTime);
		
		return account[acct] + " " + type + " " + time;
	}
	
	public static void main(String[] args) {
		
		Generator g = new Generator();
		
		for (int i = 0; i < 1000; i++) {
			System.out.println(g.nextRecord());
		}

	}
}
