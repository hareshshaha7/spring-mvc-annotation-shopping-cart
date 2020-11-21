/**
 * Author: Haresh Shaha
 * Date: 21-Nov-2020 3:10:32 pm
 */

package com.haresh.springmvcshoppingcart.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "accounts")
public class Account implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "user_name", length = 20, nullable = false)
	private String userName;

	@Column(name = "password", length = 20, nullable = false)
	private String password;

	@Column(name = "user_role", length = 10, nullable = false)
	private String userRole;

	@Column(name = "is_active", length = 1, nullable = false)
	private boolean isActive;

	public Account() {
	}

	public Account(String userName, String password, String userRole, boolean isActive) {
		this.userName = userName;
		this.password = password;
		this.userRole = userRole;
		this.isActive = isActive;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "Account [userName=" + userName + ", password=" + password + ", userRole=" + userRole + ", isActive="
				+ isActive + "]";
	}
}
