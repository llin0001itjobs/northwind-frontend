package org.llin.demo.northwind.controller.view;

@Deprecated
public class Strings extends BaseObject {

	private int stringId;

	private String stringData;

	public Strings() {
		super();
	}

	public Strings(int stringId, String stringData) {
		super();
		this.stringId = stringId;
		this.stringData = stringData;
	}

	public int getStringId() {
		return stringId;
	}

	public void setStringId(int stringId) {
		this.stringId = stringId;
	}

	public String getStringData() {
		return stringData;
	}

	public void setStringData(String stringData) {
		this.stringData = stringData;
	}

	@Override
	public String toString() {
		return "\nStrings [stringId=" + stringId + ", stringData=" + stringData + super.toString() + "]";
	}

}
