package com.intersys.kafka;

import java.util.Properties;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

public class IngestionProducer {
	
  private final Producer<Long, String> producer;
  private final String topic;
  private Long key = 0L;

  public IngestionProducer(String topic, String csBrokerList) {
	  
	  Properties props = new Properties();
	  props.put("metadata.broker.list", csBrokerList);
	  props.put("producer.type", "sync");
	  props.put("serializer.class","com.intersys.kafka.StringSupport");
	  props.put("key.serializer.class","com.intersys.kafka.LongSupport");
	  props.put("request.required.acks", "1");
	  producer = new Producer<Long, String>(new ProducerConfig(props));
	  
	  this.topic = topic;

  }
  
  public void send(String value) {
	  key++;
	  producer.send(new KeyedMessage<Long, String>(topic, key, value));
  }
  
  public void close() {
	  producer.close();
  }

}
