package com.wu.serializer;

import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;

import com.wu.util.BeanUtils;

/**
 * 
 * Kafka 自定义消息体反序列化工具类
 * 
 * @author JackWu
 * @date 2018-06-22 17:03
 */
public class DecodeingKafka implements Deserializer<Object> {

	public void configure(Map<String, ?> configs, boolean isKey) {

	}

	public Object deserialize(String topic, byte[] data) {
		return BeanUtils.byteToObject(data);
	}

	public void close() {

	}

}
