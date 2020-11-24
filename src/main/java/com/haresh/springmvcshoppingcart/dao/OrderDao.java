/**
 * Author: Haresh Shaha
 * Date: 23-Nov-2020 4:44:30 pm
 */

package com.haresh.springmvcshoppingcart.dao;

import java.util.List;

import com.haresh.springmvcshoppingcart.model.CartInfo;
import com.haresh.springmvcshoppingcart.model.OrderDetailsInfo;
import com.haresh.springmvcshoppingcart.model.OrderInfo;
import com.haresh.springmvcshoppingcart.model.PaginationResult;

public interface OrderDao {
	public void saveOrder(CartInfo cartInfo);

	public PaginationResult<OrderInfo> listOrderInfo(int page, int maxResult,
			int maxNavigationPage);

	public OrderInfo getOrderInfo(String orderId);

	public List<OrderDetailsInfo> listOrderDetailInfos(String orderId);
}
