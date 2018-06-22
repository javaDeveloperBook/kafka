package com.wu.kafka;

import com.wu.kafka.consumer.NewKafkaConsumer;
import com.wu.kafka.producer.NewKafkaProducer;

/**
 * 
 * test Kafka producer and consumer
 * 
 * @author JackWu
 * @date 2018-06-22 14:06
 */
public class TestNewKafka {

	/**
	 * 使用两个线程进行kafka消息生产和消费
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// 开启生产者线程
		new Thread(new Runnable() {
			public void run() {
				new NewKafkaProducer().produce();
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
		
		// 开启消费者线程
		new Thread(new Runnable() {
			public void run() {
				new NewKafkaConsumer().consume();
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

}
