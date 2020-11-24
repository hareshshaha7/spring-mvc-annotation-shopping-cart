/**
 * Author: Haresh Shaha
 * Date: 23-Nov-2020 5:45:53 pm
 */

package com.haresh.springmvcshoppingcart.model;

import com.haresh.springmvcshoppingcart.model.ProductInfo;

public class CartLineInfo {
	private ProductInfo productInfo;
	private int quantity;

	public CartLineInfo() {
		this.quantity = 0;
	}

	public ProductInfo getProductInfo() {
		return productInfo;
	}

	public void setProductInfo(ProductInfo productInfo) {
		this.productInfo = productInfo;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getAmount() {
		return this.quantity * this.productInfo.getPrice();
	}
}
