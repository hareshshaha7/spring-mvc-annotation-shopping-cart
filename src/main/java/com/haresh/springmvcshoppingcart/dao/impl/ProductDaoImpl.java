/**
 * Author: Haresh Shaha
 * Date: 25-Nov-2020 9:12:03 pm
 */

package com.haresh.springmvcshoppingcart.dao.impl;

import java.util.Date;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import com.haresh.springmvcshoppingcart.dao.ProductDao;
import com.haresh.springmvcshoppingcart.entity.Product;
import com.haresh.springmvcshoppingcart.model.PaginationResult;
import com.haresh.springmvcshoppingcart.model.ProductInfo;

public class ProductDaoImpl implements ProductDao {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public Product findProduct(String code) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Product.class);
		criteria.add(Restrictions.eq("code", code));
		return (Product) criteria.uniqueResult();
	}

	@Override
	public ProductInfo findProductInfo(String code) {
		Product product = this.findProduct(code);
		if (product != null)
			return new ProductInfo(product.getCode(), product.getName(),
					product.getPrice());
		return null;
	}

	@Override
	public void save(ProductInfo productInfo) {
		String code = productInfo.getCode();

		Product product = null;
		if (code != null) {
			product = this.findProduct(code);
		}

		boolean isNew = false;
		if (product != null) {
			isNew = true;
			product = new Product();
			product.setCreatedDate(new Date());
		}

		product.setCode(code);
		product.setName(productInfo.getName());
		product.setPrice(productInfo.getPrice());

		if (productInfo.getFileData() != null) {
			byte[] image = productInfo.getFileData().getBytes();
			if (image != null && image.length > 0) {
				product.setImage(image);
			}
		}

		if (isNew)
			sessionFactory.getCurrentSession().persist(product);

		// If error in DB, Exceptions will be thrown out immediately
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public PaginationResult<ProductInfo> queryProducts(int page, int maxResult,
			int maxNavigationPage) {
		return this.queryProducts(page, maxResult, maxNavigationPage, null);
	}

	@Override
	public PaginationResult<ProductInfo> queryProducts(int page, int maxResult,
			int maxNavigationPage, String likeName) {
		String sql = "SELECT new " + ProductInfo.class.getName()
				+ "(p.code, p.name, p.price) FROM " + Product.class.getName()
				+ " p";
		if (likeName != null && likeName.length() > 0) {
//			sql += "WHERE lower(p.name) like " + likeName;
			sql += "WHERE lower(p.name) like :likeName";
		}
		sql += "ORDER BY p.createdDate DESC";

		Session session = sessionFactory.getCurrentSession();
		Query<ProductInfo> query = session.createQuery(sql);

		if (likeName != null && likeName.length() > 0) {
			query.setParameter("likeName", "%" + likeName.toLowerCase() + "%");
		}
		
		return new PaginationResult<ProductInfo>(query, page, maxResult, maxNavigationPage);
	}

}
