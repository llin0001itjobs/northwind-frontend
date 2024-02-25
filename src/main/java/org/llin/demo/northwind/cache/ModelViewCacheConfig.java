package org.llin.demo.northwind.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

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
import org.llin.demo.northwind.model.Privilege;
import org.llin.demo.northwind.model.Product;
import org.llin.demo.northwind.model.ProductSupplier;
import org.llin.demo.northwind.model.PurchaseOrder;
import org.llin.demo.northwind.model.PurchaseOrderDetail;
import org.llin.demo.northwind.model.PurchaseOrderStatus;
import org.llin.demo.northwind.model.Role;
import org.llin.demo.northwind.model.Shipper;
import org.llin.demo.northwind.model.Supplier;
import org.llin.demo.northwind.model.TypeState;
import org.springframework.stereotype.Component;

@Component
public class ModelViewCacheConfig<T extends BaseObject> {

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
	private Privilege[] privileges = {};
	private Product[] products = {};
	private ProductSupplier[] productSuppliers = {};
	private PurchaseOrder[] purchaseOrders = {};
	private PurchaseOrderDetail[] purchaseOrderDetails = {};
	private PurchaseOrderStatus[] purchaseOrderStatuses = {};
	private Role[] roles = {};
	private Shipper[] shippers = {};
	private Supplier[] suppliers = {};
	private TypeState[] typeStates = {};
	
	private Map<String, Class<T[]>> mapOfTypes = new HashMap<>();
	
	private List<String> listOfTypeNames = new ArrayList<>();
	
	public ModelViewCacheConfig() {
		super();
		loadMap();
	}

	@SuppressWarnings("unchecked")
	private void loadMap() {
		Class<?> _type;
		_type = authentications.getClass();
		mapOfTypes.put("Authentication", (Class<T[]>) _type);
		_type = companies.getClass();
		mapOfTypes.put("Company", (Class<T[]>) _type);
		_type = customers.getClass();
		mapOfTypes.put("Customer", (Class<T[]>) _type);		
		_type = customerOrders.getClass();
		mapOfTypes.put("CustomerOrder", (Class<T[]>) _type);		
		_type = employees.getClass();
		mapOfTypes.put("Employee", (Class<T[]>) _type);
		_type = inventoryTransactions.getClass();
		mapOfTypes.put("InventoryTransaction", (Class<T[]>) _type);
		_type = inventoryTransactionTypes.getClass();
		mapOfTypes.put("InventoryTransactionType", (Class<T[]>) _type);
		_type = invoices.getClass();
		mapOfTypes.put("Invoice", (Class<T[]>) _type);		
		_type = orderDetails.getClass();
		mapOfTypes.put("OrderDetail", (Class<T[]>) _type);
		_type = orderDetailStatuses.getClass();
		mapOfTypes.put("OrderDetailStatus", (Class<T[]>) _type);
		_type = orderStatuses.getClass();
		mapOfTypes.put("OrderStatus", (Class<T[]>) _type);		
		_type = orderTaxStatuses.getClass();
		mapOfTypes.put("OrderTaxStatus", (Class<T[]>) _type);
		_type = privileges.getClass();
		mapOfTypes.put("Privilege", (Class<T[]>) _type);
		_type = products.getClass();
		mapOfTypes.put("Product", (Class<T[]>) _type);
		_type = productSuppliers.getClass();
		mapOfTypes.put("ProductSupplier", (Class<T[]>) _type);
		_type = purchaseOrders.getClass();
		mapOfTypes.put("PurchaseOrder", (Class<T[]>) _type);
		_type = purchaseOrderDetails.getClass();
		mapOfTypes.put("PurchaseOrderDetail", (Class<T[]>) _type);		
		_type = purchaseOrderStatuses.getClass();
		mapOfTypes.put("PurchaseOrderStatus", (Class<T[]>) _type);
		_type = roles.getClass();
		mapOfTypes.put("Role", (Class<T[]>) _type);
		_type = shippers.getClass();
		mapOfTypes.put("Shipper", (Class<T[]>) _type);			
		_type = suppliers.getClass();
		mapOfTypes.put("Supplier", (Class<T[]>) _type);
		_type = typeStates.getClass();
		mapOfTypes.put("TypeState", (Class<T[]>) _type);
		
		listOfTypeNames = new ArrayList<String>(new TreeSet<>(mapOfTypes.keySet()));
	}
	
	public Map<String, Class<T[]>> getMapOfTypes() {
		return mapOfTypes;
	}

	public List<String> getListOfTypeNames() {
		return listOfTypeNames;
	}
	
}
