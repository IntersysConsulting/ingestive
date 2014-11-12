package com.intersys.kafka.driver;

import com.intersys.data.Generator;
import com.intersys.kafka.IngestionProducer;

public class BoltLogsWriter {

	public static void main(String[] args) {

		String csBrokerList = args[0];  // "localhost:9092"
		
		Generator generator = new Generator();
		
		IngestionProducer producer = new IngestionProducer("boltLogs", csBrokerList);
		
		for (int i = 0; i < 1000000; i++) {
			producer.send(generator.nextRecord());
		}
		
		
	}

}
