package com.intersys.kafka;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.message.MessageAndMetadata;
import kafka.serializer.Decoder;
import kafka.utils.VerifiableProperties;


public class IngestionHighLevelConsumer
{
  private ConsumerConnector consumer;
  private final String topic;
  private String zookeeperConnect;
  ConsumerIterator<Long, String> it = null;
  
  public IngestionHighLevelConsumer(String topic, String groupId, String zookeeperConnect) {
	  this.topic = topic;
	  this.zookeeperConnect = zookeeperConnect;  // "127.0.0.1:2181"
	  setup(groupId);
  }
  
  public String next() {
	  return nextMetaMessage().getMessage();
  }
  
  public IngestionMetaMessage nextMetaMessage() {
	  MessageAndMetadata<Long, String> meta = it.next();
	  return new IngestionMetaMessage(meta.key(), meta.message(), meta.topic(), meta.partition(), meta.offset());
  }

  public boolean hasNext() {
	  return it.hasNext();
  }
  
  public void close() {
	  it.allDone();
  }
  
  private void setup(String groupId) {

	consumer = kafka.consumer.Consumer.createJavaConsumerConnector(
	            createConsumerConfig(groupId));
  
    // Request a single connection that gathers messages from all partitions 
	Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
    topicCountMap.put(topic, new Integer(1));
    
    // Construct the encoders/decoders for the message key and values
    Decoder<Long> keyDecoder = new LongSupport(new VerifiableProperties());
    Decoder<String> messageDecoder = new StringSupport(new VerifiableProperties());

	// Configure and request the desired streams using the topic map, encoder and decoder
    Map<String, List<KafkaStream<Long, String>>> consumerMap = consumer.createMessageStreams(topicCountMap, keyDecoder, messageDecoder);

	// We only get back one stream
    KafkaStream<Long, String> stream =  consumerMap.get(topic).get(0);
    
    // Capture the stream's iterator
    it = stream.iterator();
  }

  private ConsumerConfig createConsumerConfig(String groupId) {
	  Properties props = new Properties();
	  props.put("zookeeper.connect", zookeeperConnect);
	  props.put("group.id", groupId);
	  props.put("zookeeper.session.timeout.ms", "400");
	  props.put("zookeeper.sync.time.ms", "200");
	  props.put("auto.commit.enable", "true");
	  props.put("auto.commit.interval.ms", "1000");
	  return new ConsumerConfig(props);
  }

}