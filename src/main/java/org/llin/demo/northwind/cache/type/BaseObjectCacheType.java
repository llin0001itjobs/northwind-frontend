package org.llin.demo.northwind.cache.type;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import jakarta.annotation.PostConstruct;

import org.llin.demo.northwind._Classes_EntityObject;
import org.llin.demo.northwind.model.Authentication;
import org.llin.demo.northwind.model.EntityObject;
import org.llin.demo.northwind.model.Company;
import org.llin.demo.northwind.model.Customer;
import org.llin.demo.northwind.model.CustomerOrder;
import org.llin.demo.northwind.model.Employee;
import org.llin.demo.northwind.model.InventoryTransaction;
import org.llin.demo.northwind.model.InventoryTransactionType;
import org.llin.demo.northwind.model.Invoice;
import org.llin.demo.northwind.model.OrderDetail;
import org.llin.demo.northwind.model.OrderDetailStatus;
import org.llin.demo.northwind.model.OrderStatus;
import org.llin.demo.northwind.model.OrderTaxStatus;
import org.llin.demo.northwind.model.PaymentType;
import org.llin.demo.northwind.model.Privilege;
import org.llin.demo.northwind.model.Product;
import org.llin.demo.northwind.model.PurchaseOrder;
import org.llin.demo.northwind.model.PurchaseOrderDetail;
import org.llin.demo.northwind.model.PurchaseOrderStatus;
import org.llin.demo.northwind.model.Role;
import org.llin.demo.northwind.model.Shipper;
import org.llin.demo.northwind.model.Supplier;
import org.llin.demo.northwind.model.TypeState;
import org.springframework.stereotype.Component;

@Component
public class BaseObjectCacheType<T extends EntityObject> implements _Classes_EntityObject {
	private Authentication[] authentications = {};
	private Company[] companies = {};
	private Customer[] customers = {};
	private CustomerOrder[] customerOrders = {};
	private Employee[] employees = {};
	private InventoryTransaction[] inventoryTransactions = {};
	private InventoryTransactionType[] inventoryTransactionTypes = {};
	private Invoice[] invoices = {};
	private OrderDetail[] orderDetails = {};
	private OrderDetailStatus[] orderDetailStatuses = {};
	private OrderStatus[] orderStatuses = {};
	private OrderTaxStatus[] orderTaxStatuses = {};
	private PaymentType[] paymentTypes = {};
	private Privilege[] privileges = {};
	private Product[] products = {};
	private PurchaseOrder[] purchaseOrders = {};
	private PurchaseOrderDetail[] purchaseOrderDetails = {};
	private PurchaseOrderStatus[] purchaseOrderStatuses = {};
	private Role[] roles = {};
	private Shipper[] shippers = {};
	private Supplier[] suppliers = {};
	private TypeState[] typeStates = {};	
	Map<String, Class<T[]>> mapOfBaseType = new HashMap<>();
	List<String> listOfTypeName = new ArrayList<>();
	
	@SuppressWarnings("unchecked")
	@PostConstruct
	void init() {
		Class<?> _type;

		_type = authentications.getClass();
		mapOfBaseType.put(AUTHENTICATION, (Class<T[]>) _type);
		_type = companies.getClass();
		mapOfBaseType.put(COMPANY, (Class<T[]>) _type);
		_type = customers.getClass();
		mapOfBaseType.put(CUSTOMER, (Class<T[]>) _type);
		_type = customerOrders.getClass();
		mapOfBaseType.put(CUSTOMER_ORDER, (Class<T[]>) _type);
		_type = employees.getClass();
		mapOfBaseType.put(EMPLOYEE, (Class<T[]>) _type);
		_type = inventoryTransactions.getClass();
		mapOfBaseType.put(INVENTORY_TRANSACTION, (Class<T[]>) _type);
		_type = inventoryTransactionTypes.getClass();
		mapOfBaseType.put(INVENTORY_TRANSACTION_TYPE, (Class<T[]>) _type);
		_type = invoices.getClass();
		mapOfBaseType.put(INVOICE, (Class<T[]>) _type);
		_type = orderDetails.getClass();
		mapOfBaseType.put(ORDER_DETAIL, (Class<T[]>) _type);
		_type = orderDetailStatuses.getClass();
		mapOfBaseType.put(ORDER_DETAIL_STATUS, (Class<T[]>) _type);
		_type = orderStatuses.getClass();
		mapOfBaseType.put(ORDER_STATUS, (Class<T[]>) _type);
		_type = orderTaxStatuses.getClass();
		mapOfBaseType.put(ORDER_TAX_STATUS, (Class<T[]>) _type);
		_type = paymentTypes.getClass();
		mapOfBaseType.put(PAYMENT_TYPE, (Class<T[]>) _type);
		_type = privileges.getClass();
		mapOfBaseType.put(PRIVILEGE, (Class<T[]>) _type);
		_type = products.getClass();
		mapOfBaseType.put(PRODUCT, (Class<T[]>) _type);
		_type = purchaseOrders.getClass();
		mapOfBaseType.put(PURCHASE_ORDER, (Class<T[]>) _type);
		_type = purchaseOrderDetails.getClass();
		mapOfBaseType.put(PURCHASE_ORDER_DETAIL, (Class<T[]>) _type);
		_type = purchaseOrderStatuses.getClass();
		mapOfBaseType.put(PURCHASE_ORDER_STATUS, (Class<T[]>) _type);
		_type = roles.getClass();
		mapOfBaseType.put(ROLE, (Class<T[]>) _type);
		_type = shippers.getClass();
		mapOfBaseType.put(SHIPPER, (Class<T[]>) _type);
		_type = suppliers.getClass();
		mapOfBaseType.put(SUPPLIER, (Class<T[]>) _type);
		_type = typeStates.getClass();
		mapOfBaseType.put(TYPE_STATE, (Class<T[]>) _type);
				
		listOfTypeName = new ArrayList<String>(new TreeSet<>(mapOfBaseType.keySet()));		
	}

	public Map<String, Class<T[]>> getMapOfBaseType() {
		return this.mapOfBaseType;
	}

	public List<String> getKeyOfBaseType() {
		return this.listOfTypeName;
	}
	

}
