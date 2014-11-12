package com.intersys.kafka;

public class IngestionMetaMessage {

	private Long			key;
	private	String			message;
	private String			topic;
	private int				partition;
	private long			offset;
	
	public IngestionMetaMessage(Long key, String message, String topic, int partition, long offset) {
		this.key		= key;
		this.message	= message;
		this.topic		= topic;
		this.partition 	= partition;
		this.offset 	= offset;
	}

	public Long getKey() {
		return key;
	}

	public String getMessage() {
		return message;
	}

	public String getTopic() {
		return topic;
	}

	public int getPartition() {
		return partition;
	}

	public long getOffset() {
		return offset;
	}

}
