/**
 * Author: Haresh Shaha
 * Date: 23-Nov-2020 5:48:43 pm
 */

package com.haresh.springmvcshoppingcart.model;

import java.util.ArrayList;
import java.util.List;

import com.haresh.springmvcshoppingcart.model.CustomerInfo;
import com.haresh.springmvcshoppingcart.model.ProductInfo;

public class CartInfo {
	private int orderNum;

	private CustomerInfo customerInfo;

	private final List<CartLineInfo> cartLines = new ArrayList<CartLineInfo>();

	public CartInfo() {
	}

	public int getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}

	public CustomerInfo getCustomerInfo() {
		return customerInfo;
	}

	public void setCustomerInfo(CustomerInfo customerInfo) {
		this.customerInfo = customerInfo;
	}

	public List<CartLineInfo> getCartLineInfos() {
		return cartLines;
	}

	private CartLineInfo findLineByCode(String code) {
		for (CartLineInfo cartLineInfo : this.cartLines) {
			if (cartLineInfo.getProductInfo().getCode() == code)
				return cartLineInfo;
		}
		return null;
	}

	public void addProduct(ProductInfo productInfo, int quantity) {
		CartLineInfo cartLine = findLineByCode(productInfo.getCode());
		if (cartLine != null) {
			int newQuantity = cartLine.getQuantity() + quantity;
			if (newQuantity > 0)
				cartLine.setQuantity(newQuantity);
			else
				this.cartLines.remove(cartLine);
		} else {
			cartLine = new CartLineInfo();
			cartLine.setProductInfo(productInfo);
			cartLine.setQuantity(quantity);
			this.cartLines.add(cartLine);
		}
	}

	public void updateProduct(String code, int quantity) {
		CartLineInfo cartLine = findLineByCode(code);
		if (cartLine != null) {
			if (quantity > 0)
				cartLine.setQuantity(quantity);
			else
				this.cartLines.remove(cartLine);
		}
	}

	public void removeProduct(ProductInfo productInfo) {
		CartLineInfo cartLine = findLineByCode(productInfo.getCode());
		if (cartLine != null)
			this.cartLines.remove(cartLine);
	}

	public boolean isEmpty() {
		return this.cartLines.isEmpty();
	}

	public boolean isValidCustomer() {
		return this.customerInfo != null && this.customerInfo.isValid();
	}

	public int getQuantityTotal() {
		int totalQuantity = 0;
		for (CartLineInfo cartLineInfo : this.cartLines) {
			totalQuantity += cartLineInfo.getQuantity();
		}
		return totalQuantity;
	}

	public double getAmountTotal() {
		int totalAmount = 0;
		for (CartLineInfo cartLineInfo : this.cartLines) {
			totalAmount += cartLineInfo.getAmount();
		}
		return totalAmount;
	}

	public void updateQuantity(CartInfo cartForm) {
		if (cartForm != null) {
			List<CartLineInfo> newCartLineInfos = cartForm.getCartLineInfos();
			for (CartLineInfo cartLineInfo : newCartLineInfos) {
				this.updateProduct(cartLineInfo.getProductInfo().getCode(),
						cartLineInfo.getQuantity());
			}
		}
	}
}
