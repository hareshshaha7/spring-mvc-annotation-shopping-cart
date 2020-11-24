/**
 * Author: Haresh Shaha
 * Date: 23-Nov-2020 4:47:56 pm
 */

package com.haresh.springmvcshoppingcart.dao;

import com.haresh.springmvcshoppingcart.entity.Product;
import com.haresh.springmvcshoppingcart.model.PaginationResult;
import com.haresh.springmvcshoppingcart.model.ProductInfo;

public interface ProductDao {

	public Product findProduct(String code);

	public ProductInfo findProductInfo(String code);

	public PaginationResult<ProductInfo> queryProducts(int page, int maxResult,
			int maxNavigationPage);

	public PaginationResult<ProductInfo> queryProducts(int page, int maxResult,
			int maxNavigationPage, String likeName);

	public void save(ProductInfo productInfo);

}