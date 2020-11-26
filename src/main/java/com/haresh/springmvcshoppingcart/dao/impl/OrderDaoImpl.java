/**
 * Author: Haresh Shaha
 * Date: 26-Nov-2020 12:17:52 am
 */

package com.haresh.springmvcshoppingcart.dao.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import com.haresh.springmvcshoppingcart.dao.OrderDao;
import com.haresh.springmvcshoppingcart.entity.Order;
import com.haresh.springmvcshoppingcart.entity.OrderDetails;
import com.haresh.springmvcshoppingcart.entity.Product;
import com.haresh.springmvcshoppingcart.model.CartInfo;
import com.haresh.springmvcshoppingcart.model.CartLineInfo;
import com.haresh.springmvcshoppingcart.model.CustomerInfo;
import com.haresh.springmvcshoppingcart.model.OrderDetailsInfo;
import com.haresh.springmvcshoppingcart.model.OrderInfo;
import com.haresh.springmvcshoppingcart.model.PaginationResult;

public class OrderDaoImpl implements OrderDao {

	@Autowired
	SessionFactory sessionFactory;

	private int getMaxOrderNum() {
		String sql = "SELECT MAX(o.order_num) FROM " + Order.class.getName()
				+ " o";

		Session session = sessionFactory.getCurrentSession();
		Query<Integer> query = session.createQuery(sql);
		Integer mexOrderNum = query.uniqueResult();
		if (mexOrderNum != null)
			return mexOrderNum;
		return 0;
	}

	@Override
	public void saveOrder(CartInfo cartInfo) {
		Session session = sessionFactory.getCurrentSession();

		int orderNum = this.getMaxOrderNum() + 1;
		Order order = new Order();
		order.setId(UUID.randomUUID().toString());
		order.setOrderNum(orderNum);
		order.setOrderDate(new Date());
		order.setAmount(cartInfo.getAmountTotal());

		CustomerInfo customerInfo = cartInfo.getCustomerInfo();
		order.setCustomerName(customerInfo.getName());
		order.setCustomerPhone(customerInfo.getPhone());
		order.setCustomerEmail(customerInfo.getEmail());
		order.setCustomerAddress(customerInfo.getAddress());

		session.persist(order);

		List<CartLineInfo> cartLineInfos = cartInfo.getCartLineInfos();
		for (CartLineInfo cartLineInfo : cartLineInfos) {

			OrderDetails details = new OrderDetails();
			details.setId(UUID.randomUUID().toString());
			details.setOrder(order);
			details.setQuanity(cartLineInfo.getQuantity());
			details.setPrice(cartLineInfo.getProductInfo().getPrice());
			details.setAmount(cartLineInfo.getAmount());

			String code = cartLineInfo.getProductInfo().getCode();
			Product product = new ProductDaoImpl().findProduct(code);
			details.setProduct(product);

			session.persist(order);
		}

		// Set OrderNum for report.
		cartInfo.setOrderNum(orderNum);
	}

	@Override
	public PaginationResult<OrderInfo> listOrderInfo(int page, int maxResult,
			int maxNavigationPage) {
		String sql = "Select new " + OrderInfo.class.getName()//
				+ "(ord.id, ord.orderDate, ord.orderNum, ord.amount, "
				+ " ord.customerName, ord.customerAddress, ord.customerEmail, ord.customerPhone) "
				+ " from " + Order.class.getName() + " ord "//
				+ " order by ord.orderNum desc";
		Session session = this.sessionFactory.getCurrentSession();

		Query<OrderInfo> query = session.createQuery(sql);

		return new PaginationResult<OrderInfo>(query, page, maxResult,
				maxNavigationPage);
	}

	public Order findOrder(String orderId) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Order.class.getName());
		criteria.add(Restrictions.eq("id", orderId));
		return (Order) criteria.uniqueResult();
	}

	@Override
	public OrderInfo getOrderInfo(String orderId) {
		Order order = this.findOrder(orderId);
		if (order == null) {
			return null;
		}

		return new OrderInfo(order.getId(), order.getOrderNum(),
				order.getOrderDate(), order.getAmount(),
				order.getCustomerName(), order.getCustomerAddress(),
				order.getCustomerEmail(), order.getCustomerPhone());
	}

	@Override
	public List<OrderDetailsInfo> listOrderDetailInfos(String orderId) {
		String sql = "SELECT new " + OrderDetailsInfo.class.getName()
				+ "(d.id, d.product.code, d.product.name, d.quanity, d.price, d.amount) FROM "
				+ OrderDetails.class.getName()
				+ " d WHERE d.order.id == :orderId";

		Session session = sessionFactory.getCurrentSession();
		Query<OrderDetailsInfo> query = session.createQuery(sql);
		query.setParameter("orderId", orderId);

		return query.list();
	}
}
