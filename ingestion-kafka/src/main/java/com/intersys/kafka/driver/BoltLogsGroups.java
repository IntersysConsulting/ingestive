package com.intersys.kafka.driver;

import com.intersys.kafka.IngestionHighLevelConsumer;
import com.intersys.kafka.IngestionMetaMessage;

public class BoltLogsGroups {

	public static void main(String[] args) {

		read("groupFred",0L);
//		read("groupBiff",0L);
//		read("groupWanda",0L);
//		read("groupMike",0L);
//		read("groupSam",0L);

	}
	
	public static void read(String group, long waitBeforeStarting) {

		String zookeeperConnect = "127.0.0.1:2181";
		
		IngestionHighLevelConsumer consumer = new IngestionHighLevelConsumer("boltLogs", group, zookeeperConnect);
		
		Long begin = null;

		// Wait no more than 30 seconds until the first message on the topic
		long waitUntil = System.currentTimeMillis() + waitBeforeStarting;
		while (!consumer.hasNext() && (System.currentTimeMillis() < waitUntil)) {}
		
		// Read messages until there are no more.  Then stop.
		int i = 0;
		while (consumer.hasNext()) {
			try {
				i++;
				IngestionMetaMessage imm = consumer.nextMetaMessage();
				if (begin == null) { begin = System.currentTimeMillis(); }
				long duration = System.currentTimeMillis() - begin;
//				System.out.println("["+i+"; "+duration+"] "+imm.getMessage());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		consumer.close();
		System.out.println("Read "+i+" records as group "+group);
	}

}
