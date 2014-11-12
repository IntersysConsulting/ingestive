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

public class Event implements Serializable, Comparable<Event> {

	private String _id;
	private Long _time;
	private Integer _type;
	private String _json;

	public Event(Tuple tuple) {
		_id = tuple.getString(0);
		_time = tuple.getLong(1);
		_type = tuple.getInteger(2);
		_json = tuple.getString(3);
	}

	public Event() {

	}

	public Event(JSONObject json) throws DataException {
		try { json = json.getJSONObject("Event"); } 
		catch (Throwable t) {
			throw new DataException("Invalid JSON structure for Event constructor");
		}
		if (json.has("id")) {
			try { _id = json.getString("id"); } catch (Throwable t) {}
		}
		if (json.has("time")) {
			try { _time = json.getLong("time"); } catch (Throwable t) {}
		}
		if (json.has("type")) {
			try { _type = json.getInt("type"); } catch (Throwable t) {}
		}
		if (json.has("json")) {
			try { _json = json.getString("json"); } catch (Throwable t) {}
		}
	}

	public Event(String _id, Long _time, Integer _type, String _json) {	
		this._id = _id;
		this._time = _time;
		this._type = _type;
		this._json = _json;
	}

	public Values asValues() {
		return new Values(_id, _time, _type, _json);
	}

	public String getId() { 
		return _id;
	}
	
	public void setId(String value) {
		this._id = value;
	}

	public Long getTime() { 
		return _time;
	}
	
	public void setTime(Long value) {
		this._time = value;
	}

	public Integer getType() { 
		return _type;
	}
	
	public void setType(Integer value) {
		this._type = value;
	}

	public String getJson() { 
		return _json;
	}
	
	public void setJson(String value) {
		this._json = value;
	}

	private void writeObject(java.io.ObjectOutputStream out) throws IOException {

		if (_id == null) {
			out.writeBoolean(false);
		} else {
			out.writeBoolean(true);
			out.writeInt(_id.length());
			out.write(_id.getBytes());
		}

		if (_time == null) {
			out.writeBoolean(false);
		} else {
			out.writeBoolean(true);
			out.writeLong(_time);
		}

		if (_type == null) {
			out.writeBoolean(false);
		} else {
			out.writeBoolean(true);
			out.writeInt(_type);
		}

		if (_json == null) {
			out.writeBoolean(false);
		} else {
			out.writeBoolean(true);
			out.writeInt(_json.length());
			out.write(_json.getBytes());
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

		if (in.readBoolean()) {
			_time = in.readLong();
		} else {
			_time = null;
		}

		if (in.readBoolean()) {
			_type = in.readInt();
		} else {
			_type = null;
		}

		if (in.readBoolean()) {
			byte b[] = new byte[in.readInt()];
			in.read(b);
			_json = new String(b);
		} else {
			_json = null;
		}

	}
	
	@Override
	public String toString() {

		StringBuffer sb = new StringBuffer();
		sb.append("Event [id = " + _id + "; time = " + _time + "; type = " + _type + "; json = " + _json + "]");
		return sb.toString();
		
	}
	
	public JSONObject toJson() throws DataException {
	
		JSONObject json = new JSONObject();

		try { 
 			if (_id != null) {
 				json.putOpt("id", _id);
 			}
 			if (_time != null) {
 				json.putOpt("time", _time);
 			}
 			if (_type != null) {
 				json.putOpt("type", _type);
 			}
 			if (_json != null) {
 				json.putOpt("json", _json);
 			}
 		} catch (JSONException e) {
 			throw new DataException("JSON error when persisting Event to JSON",e);
 		}
 		
 		JSONObject result = new JSONObject();
		try { 		
			result.put("Event", json);
 		} catch (JSONException e) {
 			throw new DataException("JSON error when persisting Event to JSON",e);
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
		return new Fields("id", "time", "type", "json");
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

		if (obj instanceof Event) {
			Event other = (Event) obj;
		
		}

		return super.equals(obj);

		// End equals logic 

	}

	public int compareTo(Event o) {

		// Begin compare logic 


		// return -1 if this < that
		//         0 if this = that
		//         1 if this > that

		if (o instanceof Event) {
			Event other = (Event) o;
		
		}
	
		return 0;

		// End compare logic 

	}

	public static Event sample() {
	
		return new Event("Howdy", 1L, 0, "Howdy");
	
	}

// Begin custom methods 


// End custom methods 
	
}