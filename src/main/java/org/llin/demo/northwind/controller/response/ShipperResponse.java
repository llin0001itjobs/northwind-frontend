package org.llin.demo.northwind.controller.response;

import org.llin.demo.northwind.model.entity.EntityObject;
import org.llin.demo.northwind.model.entity.Shipper;

public class ShipperResponse extends BaseResponse {

	private Shipper[] shippers;

	public Shipper[] getShippers() {
		return shippers;
	}

	public void setShippers(Shipper[] shippers) {
		this.shippers = shippers;
	}

	@Override
	public EntityObject[] getResponse() {
		return shippers;
	}

}