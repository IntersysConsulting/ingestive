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

public class RawRecord implements Serializable, Comparable<RawRecord> {

	private String _originalRecord;

	public RawRecord(Tuple tuple) {
		_originalRecord = tuple.getString(0);
	}

	public RawRecord() {

	}

	public RawRecord(JSONObject json) throws DataException {
		try { json = json.getJSONObject("RawRecord"); } 
		catch (Throwable t) {
			throw new DataException("Invalid JSON structure for RawRecord constructor");
		}
		if (json.has("originalRecord")) {
			try { _originalRecord = json.getString("originalRecord"); } catch (Throwable t) {}
		}
	}

	public RawRecord(String _originalRecord) {	
		this._originalRecord = _originalRecord;
	}

	public Values asValues() {
		return new Values(_originalRecord);
	}

	public String getOriginalRecord() { 
		return _originalRecord;
	}
	
	public void setOriginalRecord(String value) {
		this._originalRecord = value;
	}

	private void writeObject(java.io.ObjectOutputStream out) throws IOException {

		if (_originalRecord == null) {
			out.writeBoolean(false);
		} else {
			out.writeBoolean(true);
			out.writeInt(_originalRecord.length());
			out.write(_originalRecord.getBytes());
		}

	}

	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {

		if (in.readBoolean()) {
			byte b[] = new byte[in.readInt()];
			in.read(b);
			_originalRecord = new String(b);
		} else {
			_originalRecord = null;
		}

	}
	
	@Override
	public String toString() {

		StringBuffer sb = new StringBuffer();
		sb.append("RawRecord [originalRecord = " + _originalRecord + "]");
		return sb.toString();
		
	}
	
	public JSONObject toJson() throws DataException {
	
		JSONObject json = new JSONObject();

		try { 
 			if (_originalRecord != null) {
 				json.putOpt("originalRecord", _originalRecord);
 			}
 		} catch (JSONException e) {
 			throw new DataException("JSON error when persisting RawRecord to JSON",e);
 		}
 		
 		JSONObject result = new JSONObject();
		try { 		
			result.put("RawRecord", json);
 		} catch (JSONException e) {
 			throw new DataException("JSON error when persisting RawRecord to JSON",e);
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
		return new Fields("originalRecord");
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

		if (obj instanceof RawRecord) {
			RawRecord other = (RawRecord) obj;
		
		}

		return super.equals(obj);

		// End equals logic 

	}

	public int compareTo(RawRecord o) {

		// Begin compare logic 


		// return -1 if this < that
		//         0 if this = that
		//         1 if this > that

		if (o instanceof RawRecord) {
			RawRecord other = (RawRecord) o;
		
		}
	
		return 0;

		// End compare logic 

	}

	public static RawRecord sample() {
	
		return new RawRecord("Howdy");
	
	}

// Begin custom methods 


// End custom methods 
	
}