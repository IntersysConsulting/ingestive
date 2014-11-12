package com.intersys.util;

public class AlarmClock {

	private long duration;
	private long interval;
	private long completion;
	private long whenStarted;
	
	private boolean armed = true;
	
	private static AlarmClock current = null;
	
	public static void go(long duration, long interval) {
		current = new AlarmClock(duration, interval);
		current.start();
	}
	
	public static void stop() {
		if (current == null) { return; }
		current.disarm();
	}
	
	public static String timestamp() {
		if (current == null) { return ""; }
		return current.timestamp0();
	}
	
	private AlarmClock(long duration, long interval) {
		this.duration = duration;
		this.interval = interval;
	}
	
	private void start() {
		
		whenStarted = System.currentTimeMillis();
		
		completion = whenStarted + duration;
		long now = whenStarted;
		
		while (armed && (now < completion)) {
			try { Thread.sleep(1000); } catch (InterruptedException e) { } 
			now = System.currentTimeMillis();
		}
		
		long left = completion - System.currentTimeMillis();
		if (left > 0) {
			try { Thread.sleep(left); } catch (InterruptedException e) { } 
		}
		
	}
	
	private void disarm() {
		armed = false;
	}
	
	private String timestamp0() {
		return timestamp0(System.currentTimeMillis());
	}
	
	private String timestamp0(long now) {
		String sign = "";
		long left = completion - now;
		if (left < 0L) {
			sign = "-";
			left = -left;
		}
		
		long rem = left % 1000;
		left = (left - rem) / 1000;
		String buffer = "";
		
		rem = left % 60;
		left = (left - rem) / 60;
		buffer = ":" + String.valueOf((100+rem)).substring(1);
		
		rem = left % 60;
		left = (left - rem) / 60;
		buffer = ":" + String.valueOf((100+rem)).substring(1) + buffer;
		
		buffer = String.valueOf(left)+buffer;
		
		return sign + buffer;
	}
	
	public static void main(String[] args) {
		AlarmClock.go(120000, 15000);
	}

}
