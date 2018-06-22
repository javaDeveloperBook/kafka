package com.wu.kafka.producer;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

/**
 * 
 * KAFKA入门生产者DEMO <br>
 * 新的方式实现生产者 KafkaProducer
 * 
 * @author JackWu
 * @date 2018-06-22 10:36
 */
public class NewKafkaProducer {

	/**
	 * 生产者
	 */
	private KafkaProducer<String, String> producer = null;
	/**
	 * 消息Topic
	 */
	public final static String TOPIC = "topic01";

	/**
	 * 构造方法，实例化Producer
	 */
	public NewKafkaProducer() {
		// 配置KAFKA生产者信息
		Properties properties = new Properties();
		// KAFKA服务地址和端口
		properties.put("bootstrap.servers", "127.0.0.1:9092");
		properties.put("acks", "all");
		properties.put("retries", 0);
		properties.put("batch.size", 16384);
		properties.put("linger.ms", 1);
		properties.put("buffer.memory", 33554432);
		// 设置 Key 序列化类
		properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		// 设置 Value 序列化类
		properties.setProperty("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		producer = new KafkaProducer<String, String>(properties);
	}

	/**
	 * 生产者发送Kafka消息
	 */
	public void produce() {
		for (int i = 0; i < 100; i++) {
			String key = "No." + i;
			String value = "KafaKa Message " + i;
			ProducerRecord<String, String> record = new ProducerRecord<String, String>(TOPIC, key, value);
			producer.send(record);
			System.out.println("New Producer : " + key + " = " + value);
			try {
				Thread.sleep(800);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		producer.close();
	}
}
