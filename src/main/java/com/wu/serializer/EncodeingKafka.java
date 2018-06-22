package com.wu.serializer;

import java.util.Map;

import org.apache.kafka.common.serialization.Serializer;

import com.wu.util.BeanUtils;

/**
 * 
 * Kafka自定义消息体序列化工具类
 * 
 * @author JackWu
 * @date 2018-06-22 16:48
 */
public class EncodeingKafka implements Serializer<Object> {

	public void configure(Map<String, ?> configs, boolean isKey) {

	}

	public byte[] serialize(String topic, Object data) {
		return BeanUtils.objectToByte(data);
	}

	public void close() {

	}

}
