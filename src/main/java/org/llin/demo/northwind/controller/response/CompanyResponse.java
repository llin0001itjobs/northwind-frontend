package org.llin.demo.northwind.controller.response;

import org.llin.demo.northwind.model.EntityObject;
import org.llin.demo.northwind.model.Company;

public class CompanyResponse extends BaseResponse {
	
    private Company[] companies;

	public Company[] getCompanies() {
		return companies;
	}

	public void setCompanies(Company[] companies) {
		this.companies = companies;
	}

	@Override
	public EntityObject[] getResponse() {
		return companies;
	}


}
