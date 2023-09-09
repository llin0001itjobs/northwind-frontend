package org.llin.demo.northwind.controller.view;

import org.llin.demo.northwind.util.StringUtil;

public class Customer extends BaseObject {
	
	private String lastName;

	private String firstName;

	private String emailAddress;

	private String jobTitle;

	private String businessPhone;

	private String homePhone;

	private String mobilePhone;

	private String faxNumber;

	private String address1;
	
	private String address2;
	
	private String city;

	private String stateProvince;

	private String zipPostalCode;

	private String countryRegion;

	private String notes;

	private String webSiteTitle;

	private String webSiteUrl;

	private String portraitPath;

	private String portraitTitle;
	
	private Company company;
	
	public Customer() {
		super();
	}
	
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	
	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getBusinessPhone() {
		return businessPhone;
	}

	public void setBusinessPhone(String businessPhone) {
		this.businessPhone = businessPhone;
	}

	public String getHomePhone() {
		return homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getFaxNumber() {
		return faxNumber;
	}

	public void setFaxNumber(String faxNumber) {
		this.faxNumber = faxNumber;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStateProvince() {
		return stateProvince;
	}

	public void setStateProvince(String stateProvince) {
		this.stateProvince = stateProvince;
	}

	public String getZipPostalCode() {
		return zipPostalCode;
	}

	public void setZipPostalCode(String zipPostalCode) {
		this.zipPostalCode = zipPostalCode;
	}

	public String getCountryRegion() {
		return countryRegion;
	}

	public void setCountryRegion(String countryRegion) {
		this.countryRegion = countryRegion;
	}

	public String getNotes() {
		if (!isAbridged()) {
			setAbridgedNotes(StringUtil.addEllipsis(notes));
			setAbridged(true);
		}				
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
		if (!isAbridged()) {
			setAbridgedNotes(StringUtil.addEllipsis(notes));
			setAbridged(true);
		} else {
			setAbridgedNotes(notes);
		}		
	}
	
	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getWebSiteTitle() {
		return webSiteTitle;
	}

	public void setWebSiteTitle(String webSiteTitle) {
		this.webSiteTitle = webSiteTitle;
	}

	public String getWebSiteUrl() {
		return webSiteUrl;
	}

	public void setWebSiteUrl(String webSiteUrl) {
		this.webSiteUrl = webSiteUrl;
	}

	public String getPortraitPath() {
		return portraitPath;
	}

	public void setPortraitPath(String portraitPath) {
		this.portraitPath = portraitPath;
	}

	public String getPortraitTitle() {
		return portraitTitle;
	}

	public void setPortraitTitle(String portraitTitle) {
		this.portraitTitle = portraitTitle;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	@Override
	public String toString() {
		return "\nCustomer [lastName=" + lastName + ", firstName=" + firstName + ", emailAddress=" + emailAddress
				+ ", jobTitle=" + jobTitle + ", businessPhone=" + businessPhone + ", homePhone=" + homePhone
				+ ", mobilePhone=" + mobilePhone + ", faxNumber=" + faxNumber + ", address1=" + address1 + ", address2="
				+ address2 + ", city=" + city + ", stateProvince=" + stateProvince + ", zipPostalCode=" + zipPostalCode
				+ ", countryRegion=" + countryRegion + ", notes=" + notes + ", webSiteTitle=" + webSiteTitle
				+ ", webSiteUrl=" + webSiteUrl + ", portraitPath=" + portraitPath + ", portraitTitle=" + portraitTitle
				+ ", company=" + company + super.toString() + "]";
	}

}
