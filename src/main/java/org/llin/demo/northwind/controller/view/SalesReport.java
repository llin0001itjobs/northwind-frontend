package org.llin.demo.northwind.controller.view;

@Deprecated
public class SalesReport  extends BaseObject {

	private String groupBy;

	private String display;
	private String title;

	private String filterRowSource;

	private boolean defaultData;

	public SalesReport() {
		super();
	}

	public SalesReport(String groupBy, String display, String title, String filterRowSource, boolean defaultData) {
		super();
		this.groupBy = groupBy;
		this.display = display;
		this.title = title;
		this.filterRowSource = filterRowSource;
		this.defaultData = defaultData;
	}

	public String getGroupBy() {
		return groupBy;
	}

	public void setGroupBy(String groupBy) {
		this.groupBy = groupBy;
	}

	public String getDisplay() {
		return display;
	}

	public void setDisplay(String display) {
		this.display = display;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFilterRowSource() {
		return filterRowSource;
	}

	public void setFilterRowSource(String filterRowSource) {
		this.filterRowSource = filterRowSource;
	}

	public boolean isDefaultData() {
		return defaultData;
	}

	public void setDefaultData(boolean defaultData) {
		this.defaultData = defaultData;
	}

	@Override
	public String toString() {
		return "\nSalesReports [groupBy=" + groupBy + ", display=" + display + ", title=" + title + ", filterRowSource="
				+ filterRowSource + ", defaultData=" + defaultData + super.toString() + "]";
	}

}
