/**
 * Author: Haresh Shaha
 * Date: 23-Nov-2020 5:09:29 pm
 */

package com.haresh.springmvcshoppingcart.model;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.haresh.springmvcshoppingcart.entity.Product;

public class ProductInfo {
	private String code;
	private String name;
	private double price;

	private boolean newProduct = false;

	// Upload file.
	private CommonsMultipartFile fileData;

	public ProductInfo() {
	}

	public ProductInfo(String code, String name, double price) {
		this.code = code;
		this.name = name;
		this.price = price;
	}

	public ProductInfo(Product product) {
		this.code = product.getCode();
		this.name = product.getName();
		this.price = product.getPrice();
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public boolean isNewProduct() {
		return newProduct;
	}

	public void setNewProduct(boolean newProduct) {
		this.newProduct = newProduct;
	}

	public CommonsMultipartFile getFileData() {
		return fileData;
	}

	public void setFileData(CommonsMultipartFile fileData) {
		this.fileData = fileData;
	}
}
