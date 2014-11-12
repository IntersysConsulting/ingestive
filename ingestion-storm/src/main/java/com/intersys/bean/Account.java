package com.intersys.bean;

// Begin imports 

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectStreamException;
import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.intersys.exception.DataException;

import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;

// End imports 

public class Account implements Serializable, Comparable<Account> {

	private String _id;

	public Account(Tuple tuple) {
		_id = tuple.getString(0);
	}

	public Account() {

	}

	public Account(JSONObject json) throws DataException {
		try { json = json.getJSONObject("Account"); } 
		catch (Throwable t) {
			throw new DataException("Invalid JSON structure for Account constructor");
		}
		if (json.has("id")) {
			try { _id = json.getString("id"); } catch (Throwable t) {}
		}
	}

	public Account(String _id) {	
		this._id = _id;
	}

	public Values asValues() {
		return new Values(_id);
	}

	public String getId() { 
		return _id;
	}
	
	public void setId(String value) {
		this._id = value;
	}

	private void writeObject(java.io.ObjectOutputStream out) throws IOException {

		if (_id == null) {
			out.writeBoolean(false);
		} else {
			out.writeBoolean(true);
			out.writeInt(_id.length());
			out.write(_id.getBytes());
		}

	}

	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {

		if (in.readBoolean()) {
			byte b[] = new byte[in.readInt()];
			in.read(b);
			_id = new String(b);
		} else {
			_id = null;
		}

	}
	
	@Override
	public String toString() {

		StringBuffer sb = new StringBuffer();
		sb.append("Account [id = " + _id + "]");
		return sb.toString();
		
	}
	
	public JSONObject toJson() throws DataException {
	
		JSONObject json = new JSONObject();

		try { 
 			if (_id != null) {
 				json.putOpt("id", _id);
 			}
 		} catch (JSONException e) {
 			throw new DataException("JSON error when persisting Account to JSON",e);
 		}
 		
 		JSONObject result = new JSONObject();
		try { 		
			result.put("Account", json);
 		} catch (JSONException e) {
 			throw new DataException("JSON error when persisting Account to JSON",e);
 		}
		return result;
	}

	public String toJsonString() {
		try {
			return toJson().toString();
		} catch (DataException e) {
			return "{ \"error\":\"+e.toString()+\"}";
		}
	}
	
	public static Fields asFields() {
		return new Fields("id");
	}
	
	@Override
	public int hashCode() {

		// Begin hashCode logic 

		return super.hashCode();

		// End hashCode logic 

	}

	@Override
	public boolean equals(Object obj) {

		// Begin equals logic 

		if (obj instanceof Account) {
			Account other = (Account) obj;
		
		}

		return super.equals(obj);

		// End equals logic 

	}

	public int compareTo(Account o) {

		// Begin compare logic 


		// return -1 if this < that
		//         0 if this = that
		//         1 if this > that

		if (o instanceof Account) {
			Account other = (Account) o;
		
		}
	
		return 0;

		// End compare logic 

	}

	public static Account sample() {
	
		return new Account("Howdy");
	
	}

// Begin custom methods 


// End custom methods 
	
}