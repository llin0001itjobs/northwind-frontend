package org.llin.demo.northwind.controller.response;


import org.llin.demo.northwind.model.entity.Employee;
import org.llin.demo.northwind.model.entity.EntityObject;

public class EmployeeResponse extends BaseResponse  {

	private Employee[] employees;

	public Employee[] getEmployees() {
		return employees;
	}

	public void setEmployees(Employee[] employees) {
		this.employees = employees;
	}

	@Override
	public EntityObject[] getResponse() {
		return employees;
	}
	
}
