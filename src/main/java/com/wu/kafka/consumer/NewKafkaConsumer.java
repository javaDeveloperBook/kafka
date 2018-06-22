package com.wu.kafka.consumer;

import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import com.wu.kafka.producer.NewKafkaProducer;

/**
 * 
 * Kafka消费者<br>
 * 新的方式实现消费者
 * 
 * @author JackWu
 * @date 2018-06-22 11:40
 */
public class NewKafkaConsumer {

	/**
	 * 消费者
	 */
	private KafkaConsumer<String, String> consumer;

	/**
	 * 构造器，取得消费者实例对象
	 */
	public NewKafkaConsumer() {
		Properties properties = new Properties();
		// 配置zookeeper
		properties.put("bootstrap.servers", "127.0.0.1:9092");
		// 消费组
		properties.put("group.id", "hellogroup");
		properties.put("enable.auto.commit", "true");
		properties.put("auto.commit.interval.ms", "1000");
		properties.put("session.timeout.ms", "30000");
		// 设置 Key 序列化类
		properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		// 设置 Value 序列化类
		properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		consumer = new KafkaConsumer<String, String>(properties);
		consumer.subscribe(Arrays.asList(NewKafkaProducer.TOPIC));
	}

	/**
	 * 消费者获取kafka方法
	 */
	public void consume() {
		while (true) {
			ConsumerRecords<String, String> records = consumer.poll(100);
			for (ConsumerRecord<String, String> record : records) {
				System.out.println("New Consumer : offset = " + record.offset() + " , key = " + record.key()
						+ " , value = " + record.value());
			}
		}
	}
}
