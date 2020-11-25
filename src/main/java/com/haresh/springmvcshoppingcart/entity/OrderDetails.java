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

	@Column(name = "quanity", nullable = false)
	private int quanity;

	@Column(name = "price", nullable = false)
	private double price;

	@Column(name = "amount", nullable = false)
	private double amount;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id", nullable = false, foreignKey = @ForeignKey(name = "ORDER_DETAIL_ORDER_FK"))
	private Order order;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id", nullable = false, foreignKey = @ForeignKey(name = "ORDER_DETAIL_PRODUCT_FK"))
	private Product product;

	public OrderDetails() {
	}

	public OrderDetails(String id, int quanity, double price, double amount,
			Order order, Product product) {
		super();
		this.id = id;
		this.quanity = quanity;
		this.price = price;
		this.amount = amount;
		this.order = order;
		this.product = product;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getQuanity() {
		return quanity;
	}

	public void setQuanity(int quanity) {
		this.quanity = quanity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
}
