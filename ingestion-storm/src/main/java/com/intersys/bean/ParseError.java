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

public class ParseError implements Serializable, Comparable<ParseError> {

	private String _originalReord;
	private String _message;

	public ParseError(Tuple tuple) {
		_originalReord = tuple.getString(0);
		_message = tuple.getString(1);
	}

	public ParseError() {

	}

	public ParseError(JSONObject json) throws DataException {
		try { json = json.getJSONObject("ParseError"); } 
		catch (Throwable t) {
			throw new DataException("Invalid JSON structure for ParseError constructor");
		}
		if (json.has("originalReord")) {
			try { _originalReord = json.getString("originalReord"); } catch (Throwable t) {}
		}
		if (json.has("message")) {
			try { _message = json.getString("message"); } catch (Throwable t) {}
		}
	}

	public ParseError(String _originalReord, String _message) {	
		this._originalReord = _originalReord;
		this._message = _message;
	}

	public Values asValues() {
		return new Values(_originalReord, _message);
	}

	public String getOriginalReord() { 
		return _originalReord;
	}
	
	public void setOriginalReord(String value) {
		this._originalReord = value;
	}

	public String getMessage() { 
		return _message;
	}
	
	public void setMessage(String value) {
		this._message = value;
	}

	private void writeObject(java.io.ObjectOutputStream out) throws IOException {

		if (_originalReord == null) {
			out.writeBoolean(false);
		} else {
			out.writeBoolean(true);
			out.writeInt(_originalReord.length());
			out.write(_originalReord.getBytes());
		}

		if (_message == null) {
			out.writeBoolean(false);
		} else {
			out.writeBoolean(true);
			out.writeInt(_message.length());
			out.write(_message.getBytes());
		}

	}

	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {

		if (in.readBoolean()) {
			byte b[] = new byte[in.readInt()];
			in.read(b);
			_originalReord = new String(b);
		} else {
			_originalReord = null;
		}

		if (in.readBoolean()) {
			byte b[] = new byte[in.readInt()];
			in.read(b);
			_message = new String(b);
		} else {
			_message = null;
		}

	}
	
	@Override
	public String toString() {

		StringBuffer sb = new StringBuffer();
		sb.append("ParseError [originalReord = " + _originalReord + "; message = " + _message + "]");
		return sb.toString();
		
	}
	
	public JSONObject toJson() throws DataException {
	
		JSONObject json = new JSONObject();

		try { 
 			if (_originalReord != null) {
 				json.putOpt("originalReord", _originalReord);
 			}
 			if (_message != null) {
 				json.putOpt("message", _message);
 			}
 		} catch (JSONException e) {
 			throw new DataException("JSON error when persisting ParseError to JSON",e);
 		}
 		
 		JSONObject result = new JSONObject();
		try { 		
			result.put("ParseError", json);
 		} catch (JSONException e) {
 			throw new DataException("JSON error when persisting ParseError to JSON",e);
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
		return new Fields("originalReord", "message");
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

		if (obj instanceof ParseError) {
			ParseError other = (ParseError) obj;
		
		}

		return super.equals(obj);

		// End equals logic 

	}

	public int compareTo(ParseError o) {

		// Begin compare logic 


		// return -1 if this < that
		//         0 if this = that
		//         1 if this > that

		if (o instanceof ParseError) {
			ParseError other = (ParseError) o;
		
		}
	
		return 0;

		// End compare logic 

	}

	public static ParseError sample() {
	
		return new ParseError("Howdy", "Howdy");
	
	}

// Begin custom methods 


// End custom methods 
	
}