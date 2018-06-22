package com.wu.kafka.producer;

import java.util.Properties;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

/**
 * 
 * KAFKA入门生产者DEMO
 * 
 * @author JackWu
 * @date 2018-06-22 10:36
 */
public class OldKafkaProducer {

	/**
	 * 生产者实例
	 */
	private Producer<String, String> producer = null;
	/**
	 * 消息Topic
	 */
	public final static String TOPIC = "Hello-Kafka";

	/**
	 * 构造方法，实例化Producer
	 */
	public OldKafkaProducer() {
		// 配置KAFKA生产者信息
		Properties properties = new Properties();
		// KAFKA服务地址和端口
		properties.setProperty("metadata.broker.list", "127.0.0.1:9092");
		// Zookeeper 服务地址和端口
		properties.setProperty("zk.connect", "127.0.0.1:2181");
		// 设置 Value 序列化类
		properties.setProperty("serializer.class", "kafka.serializer.StringEncoder");
		// 设置 Key 序列化类
		properties.setProperty("key.serializer.class", "kafka.serializer.StringEncoder");
		// -1 : 意味着producer在follower副本确认接收到数据后才算一次发送完成
		properties.setProperty("request.required.acks", "-1");
		producer = new Producer<String, String>(new ProducerConfig(properties));
	}

	/**
	 * 生产者发送Kafka消息
	 */
	public void produce() {
		for (int i = 0; i < 100; i++) {
			String key = "No." + i;
			String data = "KafaKa Message " + i;
			producer.send(new KeyedMessage<String, String>(TOPIC, key, data));
			try {
				System.out.println("Producer ==== " + key + " : " + data);
				Thread.sleep(1500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
