package com.wu.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * Kafka 简单消息实体类
 * 
 * @author JackWu
 * @date 2018-06-22 14:22
 */
@SuppressWarnings("serial")
public class OrderVO implements Serializable {

	/**
	 * 订单编号
	 */
	private Integer orderId;

	/**
	 * 物品名称
	 */
	private String goods;

	/**
	 * 单价
	 */
	private Double price;

	/**
	 * 数量
	 */
	private Long count;

	/**
	 * 总价
	 */
	private Double totalPrice;

	/**
	 * 创建订单时间
	 */
	private Date create;

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getGoods() {
		return goods;
	}

	public void setGoods(String goods) {
		this.goods = goods;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Date getCreate() {
		return create;
	}

	public void setCreate(Date create) {
		this.create = create;
	}

	@Override
	public String toString() {
		return "OrderVO [orderId=" + orderId + ", goods=" + goods + ", price=" + price + ", count=" + count
				+ ", totalPrice=" + totalPrice + ", create=" + create + "]";
	}

}
