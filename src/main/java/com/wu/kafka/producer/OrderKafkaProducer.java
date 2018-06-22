package com.wu.kafka.producer;

import java.util.Date;
import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import com.wu.vo.OrderVO;

/**
 * 
 * KAFKA入门生产者DEMO <br>
 * 新的方式实现生产者 KafkaProducer
 * 
 * @author JackWu
 * @date 2018-06-22 10:36
 */
public class OrderKafkaProducer {

	/**
	 * 生产者
	 */
	private KafkaProducer<String, OrderVO> producer = null;
	/**
	 * 消息Topic
	 */
	public final static String TOPIC = "topic01";

	/**
	 * 构造方法，实例化Producer
	 */
	public OrderKafkaProducer() {
		// 配置KAFKA生产者信息
		Properties properties = new Properties();
		// KAFKA服务地址和端口
		properties.put("bootstrap.servers", "127.0.0.1:9092");
		properties.put("acks", "all");
		properties.put("retries", 0);
		properties.put("batch.size", 16384);
		properties.put("linger.ms", 1);
		properties.put("buffer.memory", 33554432);
		// Zookeeper 服务地址和端口
		// properties.put("zk.connect", "127.0.0.1:2181");
		// 设置 Key 序列化类
		properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		// 设置 Value 序列化类
		properties.setProperty("value.serializer", "com.wu.serializer.EncodeingKafka");
		producer = new KafkaProducer<String, OrderVO>(properties);
	}

	/**
	 * 生产者发送Kafka消息
	 */
	public void produce() {
		for (int i = 0; i < 100; i++) {
			String key = "OrderNo." + i;
			OrderVO value = new OrderVO();
			value.setOrderId(i);
			value.setGoods("goods name " + i);
			value.setPrice(i * 10.00);
			value.setCount((long) i);
			value.setCreate(new Date());
			// 发送Kafka消息
			ProducerRecord<String, OrderVO> record = new ProducerRecord<String, OrderVO>(TOPIC, key, value);
			producer.send(record);
			System.out.println("Order Producer : " + key + " = " + value);
			try {
				Thread.sleep(800);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		producer.close();
	}
}
