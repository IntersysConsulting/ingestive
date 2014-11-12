package com.intersys.exception;

import org.codehaus.jettison.json.JSONObject;

public class GoodRecordException extends Exception {

	private JSONObject jobj;
	private String account;
	private Integer type;
	private Long time;
	
	public GoodRecordException(JSONObject jobj, String account, Integer type, Long time) {
		super();
		this.jobj = jobj;
		this.account = account;
		this.type = type;
		this.time = time;
	}

	public JSONObject getJobj() {
		return jobj;
	}

	public String getAccount() {
		return account;
	}

	public Integer getType() {
		return type;
	}

	public Long getTime() {
		return time;
	}

}
