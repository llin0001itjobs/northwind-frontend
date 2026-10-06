package org.llin.demo.northwind.controller.response;

import org.llin.demo.northwind.model.entity.Company;
import org.llin.demo.northwind.model.entity._EntityObject;

public class CompanyResponse extends BaseResponse {
	
    private Company[] companies;

	public Company[] getCompanies() {
		return companies;
	}

	public void setCompanies(Company[] companies) {
		this.companies = companies;
	}

	@Override
	public _EntityObject[] getResponse() {
		return companies;
	}


}
