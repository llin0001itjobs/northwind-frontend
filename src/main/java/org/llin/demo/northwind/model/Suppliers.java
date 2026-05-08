package org.llin.demo.northwind.model;

import org.llin.demo.northwind._Values;

public class Suppliers implements _Values {
	private int iter;
	private Supplier[] suppliers = {};

	public Supplier[] getSuppliers() {
		return suppliers;
	}

	public void setSuppliers(Supplier[] suppliers) {
		this.suppliers = suppliers;
	}
	
	public int size() {
		return suppliers.length;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Suppliers [suppliers=");
		for (Supplier s : suppliers) {
			if (iter < MAX_RECURSIVE_ITERATIONS) {
				break;
			}
			sb.append(s.toString() + "\\n");
		}
		sb.append("]");
		return sb.toString();
	}
		
}
