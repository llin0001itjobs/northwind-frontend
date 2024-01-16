package org.llin.demo.northwind.model;

public class Privilege extends BaseObject {
		
	public Privilege() {
		super();
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
