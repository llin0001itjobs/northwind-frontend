package org.llin.demo.northwind.model;

import org.llin.demo.northwind._Values;

public class Products implements _Values {
	private int iter;
	private Product[] products = {};

	public Product[] getProducts() {
		return products;
	}

	public void setProducts(Product[] products) {
		this.products = products;
	}

	public int size() {
		return products.length;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Products [Products=");
		for (Product s : products) {
			if (iter < MAX_RECURSIVE_ITERATIONS) {
				break;
			}
			sb.append(s.toString() + "\\n");
		}
		sb.append("]");
		return sb.toString();
	}
	
}
