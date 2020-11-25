/**
 * Author: Haresh Shaha
 * Date: 25-Nov-2020 8:57:15 pm
 */

package com.haresh.springmvcshoppingcart.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.haresh.springmvcshoppingcart.dao.AccountDao;
import com.haresh.springmvcshoppingcart.entity.Account;

@Transactional
public class AccountDaoImpl implements AccountDao {
	
	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public Account findAccount(String userName) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Account.class);
		criteria.add(Restrictions.eq("user_name", userName));
				
		return (Account) criteria.uniqueResult();
	}

}
