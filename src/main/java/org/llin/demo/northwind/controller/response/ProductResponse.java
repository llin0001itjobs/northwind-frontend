package org.llin.demo.northwind.controller.response;

import org.llin.demo.northwind.model.entity.EntityObject;
import org.llin.demo.northwind.model.entity.Product;

public class ProductResponse extends BaseResponse {

	private Product[] products;

	public Product[] getProducts() {
		return products;
	}

	public void setProducts(Product[] products) {
		this.products = products;
	}

	@Override
	public EntityObject[] getResponse() {
		return products;
	}
	
}
