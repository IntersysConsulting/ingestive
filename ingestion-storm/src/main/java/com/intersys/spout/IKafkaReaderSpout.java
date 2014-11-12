package com.intersys.spout;

import com.intersys.bean.*;

import java.util.Map;

public interface IKafkaReaderSpout {

    public Map<String, Object> getComponentConfiguration();

    /*
     * Unreliably emit an instance of RawRecord to stream RawRecords.  
     */
	public void emitToRawRecords(RawRecord rawRecord);


    /*
     * Reliably emit an instance of RawRecord to stream RawRecords.
     * The second parameter is to be used as a message ID for
     * notification of message ack or fail.  
     */
	public void emitToRawRecords(RawRecord rawRecord, RawRecord messageID);

    /*
     * Unreliably emit an instance of RawRecord to stream ArchiveCopies.  
     */
	public void emitToArchiveCopies(RawRecord rawRecord);


    /*
     * Reliably emit an instance of RawRecord to stream ArchiveCopies.
     * The second parameter is to be used as a message ID for
     * notification of message ack or fail.  
     */
	public void emitToArchiveCopies(RawRecord rawRecord, RawRecord messageID);

	public int getTaskId();

}
