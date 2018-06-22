package com.wu.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 
 * 
 * @author JackWu
 * @date 2018-06-22 16:51
 */
public class BeanUtils {
	/**
	 * 对象转byte
	 * 
	 * @param object
	 * @return
	 */
	public static byte[] objectToByte(Object object) {
		byte[] bt = null;
		try {
			ByteArrayOutputStream bot = new ByteArrayOutputStream();
			ObjectOutputStream ott = new ObjectOutputStream(bot);
			ott.writeObject(object);
			ott.flush();
			bt = bot.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bt;
	}

	/**
	 * byte转Object
	 * 
	 * @param bt
	 * @return
	 */
	public static Object byteToObject(byte[] bt) {
		Object object = null;
		try {
			ByteArrayInputStream bti = new ByteArrayInputStream(bt);
			ObjectInputStream ois = new ObjectInputStream(bti);
			object = ois.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return object;
	}
}
