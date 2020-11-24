/**
 * Author: Haresh Shaha
 * Date: 23-Nov-2020 4:42:30 pm
 */

package com.haresh.springmvcshoppingcart.dao;

import com.haresh.springmvcshoppingcart.entity.Account;

public interface AccountDao {
	public Account findAccount(String userName);
}
