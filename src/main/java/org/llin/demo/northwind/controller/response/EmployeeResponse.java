package org.llin.demo.northwind.controller.response;


import org.llin.demo.northwind.model.EntityObject;
import org.llin.demo.northwind.model.Employee;

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
