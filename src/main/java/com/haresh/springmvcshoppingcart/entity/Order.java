/**
 * Author: Haresh Shaha
 * Date: 21-Nov-2020 3:10:46 pm
 */

package com.haresh.springmvcshoppingcart.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "orders", uniqueConstraints = {
		@UniqueConstraint(columnNames = "order_num") })
public class Order implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", length = 50, nullable = false)
	private String id;

	@Column(name = "amount", nullable = false)
	private double amount;

	@Column(name = "customer_name", length = 255, nullable = false)
	private String customerName;

	@Column(name = "customer_email", length = 128, nullable = false)
	private String customerEmail;

	@Column(name = "customer_address", length = 255, nullable = false)
	private String customerAddress;

	@Column(name = "customer_phone", length = 128, nullable = false)
	private String customerPhone;

	@Column(name = "order_num", nullable = false)
	private int orderNum;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "order_date", nullable = false)
	private Date orderDate;

	public Order() {
	}

	public Order(String id, double amount, String customerName,
			String customerEmail, String customerAddress, String customerPhone,
			int orderNum, Date orderDate) {
		this.id = id;
		this.amount = amount;
		this.customerName = customerName;
		this.customerEmail = customerEmail;
		this.customerAddress = customerAddress;
		this.customerPhone = customerPhone;
		this.orderNum = orderNum;
		this.orderDate = orderDate;
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

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}

	public int getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", amount=" + amount + ", customerName="
				+ customerName + ", customerEmail=" + customerEmail
				+ ", customerAddress=" + customerAddress + ", customerPhone="
				+ customerPhone + ", orderNum=" + orderNum + ", orderDate="
				+ orderDate + "]";
	}
}
