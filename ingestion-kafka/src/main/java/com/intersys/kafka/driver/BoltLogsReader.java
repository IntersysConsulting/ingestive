package com.intersys.kafka.driver;

import com.intersys.kafka.IngestionHighLevelConsumer;
import com.intersys.kafka.IngestionMetaMessage;

public class BoltLogsReader {

	public static void main(String[] args) {

		String zookeeperConnect = args[0];   // "127.0.0.1:2181"
		
		IngestionHighLevelConsumer consumer = new IngestionHighLevelConsumer("boltLogs", "driver", zookeeperConnect);
		
		int i = 0;
		while (true) {
			try {
				i++;
				IngestionMetaMessage imm = consumer.nextMetaMessage();
				System.out.println("["+i+"] "+imm.getMessage());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
