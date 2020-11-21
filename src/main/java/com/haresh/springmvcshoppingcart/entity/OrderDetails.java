/**
 * Author: Haresh Shaha
 * Date: 21-Nov-2020 3:11:08 pm
 */

package com.haresh.springmvcshoppingcart.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "order_details")
public class OrderDetails implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", length = 50, nullable = false)
	private String id;

	@Column(name = "amount", nullable = false)
	private double amount;

	@Column(name = "price", nullable = false)
	private double price;

	@Column(name = "quanity", nullable = false)
	private int quanity;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id", nullable = false, foreignKey = @ForeignKey(name = "ORDER_DETAIL_ORDER_FK"))
	private String orderId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id", nullable = false, foreignKey = @ForeignKey(name = "ORDER_DETAIL_PRODUCT_FK"))
	private String productId;

	public OrderDetails() {
	}

	public OrderDetails(String id, double amount, double price, int quanity,
			String orderId, String productId) {
		this.id = id;
		this.amount = amount;
		this.price = price;
		this.quanity = quanity;
		this.orderId = orderId;
		this.productId = productId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuanity() {
		return quanity;
	}

	public void setQuanity(int quanity) {
		this.quanity = quanity;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "OrderDetails [id=" + id + ", amount=" + amount + ", price="
				+ price + ", quanity=" + quanity + ", orderId=" + orderId
				+ ", productId=" + productId + "]";
	}
}
