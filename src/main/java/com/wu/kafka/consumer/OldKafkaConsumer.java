package com.wu.kafka.consumer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.wu.kafka.producer.OldKafkaProducer;

import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.serializer.Decoder;
import kafka.serializer.StringDecoder;
import kafka.utils.VerifiableProperties;

/**
 * 
 * Kafka消费者
 * 
 * @author JackWu
 * @date 2018-06-22 11:40
 */
public class OldKafkaConsumer {

	/**
	 * 消费者连接
	 */
	private ConsumerConnector connector;

	/**
	 * 构造器，取得消费者实例对象
	 */
	public OldKafkaConsumer() {
		Properties properties = new Properties();
		// 配置zookeeper
		properties.setProperty("zookeeper.connect", "127.0.0.1:2181");
		// 消费组
		properties.setProperty("group.id", "hellogroup");
		// 连接zookeeper超时
		properties.setProperty("zookeeper.session.timeout.ms", "4000");
		properties.setProperty("zookeeper.sync.time.ms", "200");
		properties.setProperty("rebalance.max.retries", "5");
		properties.setProperty("rebalance.backoff.ms", "1200");
		properties.setProperty("auto.offset.reset", "smallest");
		// 序列化
		properties.setProperty("serializer.class", "kafka.serializer.StringEncoder");
		ConsumerConfig config = new ConsumerConfig(properties);
		connector = Consumer.createJavaConsumerConnector(config);
	}

	/**
	 * 消费者获取kafka方法
	 */
	public void consume() {
		Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
		topicCountMap.put(OldKafkaProducer.TOPIC, new Integer(1));
		Decoder<String> keyDecoder = new StringDecoder(new VerifiableProperties());
		Decoder<String> valueDecoder = new StringDecoder(new VerifiableProperties());
		Map<String, List<KafkaStream<String, String>>> consumerMap = connector.createMessageStreams(topicCountMap,
				keyDecoder, valueDecoder);
		KafkaStream<String, String> stream = consumerMap.get(OldKafkaProducer.TOPIC).get(0);
		ConsumerIterator<String, String> iterator = stream.iterator();
		while (iterator.hasNext()) {
			System.out.println("Consumer ==== " + iterator.next().message());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
