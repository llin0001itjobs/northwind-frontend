package org.llin.demo.northwind.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import javax.annotation.PostConstruct;

import org.llin.demo.northwind._Classes;
import org.llin.demo.northwind.model.Authentication;
import org.llin.demo.northwind.model.BaseObject;
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
public class ModelViewCacheType<T extends BaseObject> implements _Classes {
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

	Map<String, Class<T[]>> mapOfType = new HashMap<>();
	List<String> listOfTypeName = new ArrayList<>();

	@SuppressWarnings("unchecked")

	@PostConstruct
	void init() {
		Class<?> _type;

		_type = authentications.getClass();
		mapOfType.put(AUTHENTICATION, (Class<T[]>) _type);
		_type = companies.getClass();
		mapOfType.put(COMPANY, (Class<T[]>) _type);
		_type = customers.getClass();
		mapOfType.put(CUSTOMER, (Class<T[]>) _type);
		_type = customerOrders.getClass();
		mapOfType.put(CUSTOMER_ORDER, (Class<T[]>) _type);
		_type = employees.getClass();
		mapOfType.put(EMPLOYEE, (Class<T[]>) _type);
		_type = inventoryTransactions.getClass();
		mapOfType.put(INVENTORY_TRANSACTION, (Class<T[]>) _type);
		_type = inventoryTransactionTypes.getClass();
		mapOfType.put(INVENTORY_TRANSACTION_TYPE, (Class<T[]>) _type);
		_type = invoices.getClass();
		mapOfType.put(INVOICE, (Class<T[]>) _type);
		_type = orderDetails.getClass();
		mapOfType.put(ORDER_DETAIL, (Class<T[]>) _type);
		_type = orderDetailStatuses.getClass();
		mapOfType.put(ORDER_DETAIL_STATUS, (Class<T[]>) _type);
		_type = orderStatuses.getClass();
		mapOfType.put(ORDER_STATUS, (Class<T[]>) _type);
		_type = orderTaxStatuses.getClass();
		mapOfType.put(ORDER_TAX_STATUS, (Class<T[]>) _type);
		_type = paymentTypes.getClass();
		mapOfType.put(PAYMENT_TYPE, (Class<T[]>) _type);
		_type = privileges.getClass();
		mapOfType.put(PRIVILEGE, (Class<T[]>) _type);
		_type = products.getClass();
		mapOfType.put(PRODUCT, (Class<T[]>) _type);
		_type = purchaseOrders.getClass();
		mapOfType.put(PURCHASE_ORDER, (Class<T[]>) _type);
		_type = purchaseOrderDetails.getClass();
		mapOfType.put(PURCHASE_ORDER_DETAIL, (Class<T[]>) _type);
		_type = purchaseOrderStatuses.getClass();
		mapOfType.put(PURCHASE_ORDER_STATUS, (Class<T[]>) _type);
		_type = roles.getClass();
		mapOfType.put(ROLE, (Class<T[]>) _type);
		_type = shippers.getClass();
		mapOfType.put(SHIPPER, (Class<T[]>) _type);
		_type = suppliers.getClass();
		mapOfType.put(SUPPLIER, (Class<T[]>) _type);
		_type = typeStates.getClass();
		mapOfType.put(TYPE_STATE, (Class<T[]>) _type);

		listOfTypeName = new ArrayList<String>(new TreeSet<>(mapOfType.keySet()));
	}

	public Map<String, Class<T[]>> getMapOfTypes() {
		return this.mapOfType;
	}

	public List<String> getKeyOfTypes() {
		return this.listOfTypeName;
	}

}
