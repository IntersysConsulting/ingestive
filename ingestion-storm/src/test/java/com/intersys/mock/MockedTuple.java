package com.intersys.mock;

import java.util.List;

import backtype.storm.generated.GlobalStreamId;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.MessageId;
import backtype.storm.tuple.Tuple;

public class MockedTuple implements Tuple {

	private String sourceStreamId;
	private List<Object> values;
	
	public MockedTuple(String sourceStreamId, List<Object> values) {
		this.sourceStreamId = sourceStreamId;
		this.values = values;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int fieldIndex(String field) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean contains(String field) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object getValue(int i) {
		return values.get(i);
	}

	@Override
	public String getString(int i) {
		return (String) getValue(i);
	}

	@Override
	public Integer getInteger(int i) {
		return (Integer) getValue(i);
	}

	@Override
	public Long getLong(int i) {
		return (Long) getValue(i);
	}

	@Override
	public Boolean getBoolean(int i) {
		return (Boolean) getValue(i);
	}

	@Override
	public Short getShort(int i) {
		return (Short) getValue(i);
	}

	@Override
	public Byte getByte(int i) {
		return (Byte) getValue(i);
	}

	@Override
	public Double getDouble(int i) {
		return (Double) getValue(i);
	}

	@Override
	public Float getFloat(int i) {
		return (Float) getValue(i);
	}

	@Override
	public byte[] getBinary(int i) {
		return (byte[]) getValue(i);
	}

	@Override
	public Object getValueByField(String field) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getStringByField(String field) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getIntegerByField(String field) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getLongByField(String field) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean getBooleanByField(String field) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Short getShortByField(String field) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Byte getByteByField(String field) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Double getDoubleByField(String field) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Float getFloatByField(String field) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] getBinaryByField(String field) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object> getValues() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Fields getFields() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object> select(Fields selector) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GlobalStreamId getSourceGlobalStreamid() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSourceComponent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getSourceTask() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getSourceStreamId() {
		return sourceStreamId;
	}

	@Override
	public MessageId getMessageId() {
		// TODO Auto-generated method stub
		return null;
	}

}
