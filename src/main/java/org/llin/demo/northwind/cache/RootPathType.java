package org.llin.demo.northwind.cache;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.llin.demo.northwind._Classes;
import static org.llin.demo.northwind.util.StringUtil.deCapitalize;
import org.springframework.stereotype.Component;

@Component
public class RootPathType implements _Classes {

	private Map<String, String> rootPathCache = new HashMap<>();
		
	public Map<String, String> getRootPathCache() {
		return rootPathCache;
	}

	public void setRootPathCache(Map<String, String> rootPathCache) {
		this.rootPathCache = rootPathCache;
	}

	@PostConstruct 
	void init() { 		
		rootPathCache.put(AUTHENTICATION, AUTHENTICATIONS.toLowerCase());
		rootPathCache.put(COMPANY, COMPANIES.toLowerCase());
		rootPathCache.put(CUSTOMER, CUSTOMERS.toLowerCase());
		rootPathCache.put(CUSTOMER_ORDER, deCapitalize(CUSTOMER_ORDERS));
		rootPathCache.put(EMPLOYEE, EMPLOYEES.toLowerCase());
		rootPathCache.put(INVENTORY_TRANSACTION, deCapitalize(INVENTORY_TRANSACTIONS));
		rootPathCache.put(INVENTORY_TRANSACTION_TYPE, deCapitalize(INVENTORY_TRANSACTION_TYPES));
		rootPathCache.put(INVOICE, INVOICES.toLowerCase());
		rootPathCache.put(ORDER_DETAIL, deCapitalize(ORDER_DETAILS));
		rootPathCache.put(ORDER_DETAIL_STATUS, deCapitalize(ORDER_DETAIL_STATUSES));
		rootPathCache.put(ORDER_STATUS, deCapitalize(ORDER_STATUSES));
		rootPathCache.put(ORDER_TAX_STATUS, deCapitalize(ORDER_TAX_STATUSES));
		rootPathCache.put(PAYMENT_TYPE, deCapitalize(PAYMENT_TYPES));
		rootPathCache.put(PRIVILEGE, PRIVILEGES.toLowerCase());
		rootPathCache.put(PRODUCT, PRODUCTS.toLowerCase());
		rootPathCache.put(PURCHASE_ORDER, deCapitalize(PURCHASE_ORDERS));
		rootPathCache.put(PURCHASE_ORDER_DETAIL, deCapitalize(PURCHASE_ORDER_DETAILS));
		rootPathCache.put(PURCHASE_ORDER_STATUS, deCapitalize(PURCHASE_ORDER_STATUSES));
		rootPathCache.put(ROLE, ROLES.toLowerCase());
		rootPathCache.put(SHIPPER, SHIPPERS.toLowerCase());
		rootPathCache.put(SUPPLIER, SUPPLIERS.toLowerCase());
		rootPathCache.put(TYPE_STATE, deCapitalize(TYPE_STATES));
	}
	
}
