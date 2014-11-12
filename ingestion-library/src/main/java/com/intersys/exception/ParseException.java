package com.intersys.exception;

public class ParseException extends Exception {

	private String rawRecord;
	private String message;
	
	public ParseException(String rawRecord, String message) {
		super();
		this.rawRecord = rawRecord;
		this.message = message;
	}

	public String getRawRecord() {
		return rawRecord;
	}

	public String getMessage() {
		return message;
	}

}
