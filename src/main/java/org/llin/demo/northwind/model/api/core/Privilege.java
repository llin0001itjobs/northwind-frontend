package org.llin.demo.northwind.model.api.core;

public class Privilege extends BaseObject {

	private Privilege[] self = {};
	
	public Privilege() {
		super();
		_type = self.getClass();
		label = "Privilege"; 
	}
	
	private String privilegeName;
	
	public String getPrivilegeName() {
		return privilegeName;
	}

	public void setPrivilegeName(String privilegeName) {
		this.privilegeName = privilegeName;
	}

	@Override
	public String toString() {
		return "\nPrivilege [privilegeName=" + privilegeName + super.toString() + "]";
	}

}
