package com.intersys.kafka.driver;

import com.intersys.data.Generator;
import com.intersys.kafka.IngestionProducer;

public class RawRecordsWriter {

	public static void main(String[] args) {

		String csBrokerList = args[0];  // "localhost:9092"
		
		Generator generator = new Generator();
		
		IngestionProducer producer = new IngestionProducer("rawRecords", csBrokerList);
		
		for (int i = 0; i < 100000; i++) {
			producer.send(generator.nextRecord());
		}
		
		producer.close();
		
	}

}
