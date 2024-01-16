package org.llin.demo.northwind.model;

public class ProductSupplier extends BaseObject {

	private int product_id;
	private int supplier_id;


	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public int getSupplier_id() {
		return supplier_id;
	}

	public void setSupplier_id(int supplier_id) {
		this.supplier_id = supplier_id;
	}

	@Override
	public String toString() {
		return "\nProductSupplier [id=" + id + ", product_id=" + product_id + ", supplier_id=" + supplier_id + super.toString() + "]";
	}

}
