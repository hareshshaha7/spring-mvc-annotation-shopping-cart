/**
 * Author: Haresh Shaha
 * Date: 23-Nov-2020 4:48:52 pm
 */

package com.haresh.springmvcshoppingcart.model;

public class CustomerInfo {
	private String name;
	private String address;
	private String email;
	private String phone;
	private boolean isValid;

	public CustomerInfo() {
	}

	public CustomerInfo(String name, String address, String email, String phone,
			boolean isValid) {
		this.name = name;
		this.address = address;
		this.email = email;
		this.phone = phone;
		this.isValid = isValid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public boolean isValid() {
		return isValid;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}
}
