package org.llin.demo.northwind.model.entity;

public class Privilege extends EntityObject {
			
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
