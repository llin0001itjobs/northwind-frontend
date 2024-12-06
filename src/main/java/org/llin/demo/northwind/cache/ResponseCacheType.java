package org.llin.demo.northwind.cache;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.llin.demo.northwind._Classes;
import org.llin.demo.northwind.controller.response.AuthenticationResponse;
import org.llin.demo.northwind.controller.response.BaseResponse;
import org.llin.demo.northwind.controller.response.CompanyResponse;
import org.llin.demo.northwind.controller.response.CustomerOrderResponse;
import org.llin.demo.northwind.controller.response.CustomerResponse;
import org.llin.demo.northwind.controller.response.EmployeeResponse;
import org.llin.demo.northwind.controller.response.InventoryTransactionResponse;
import org.llin.demo.northwind.controller.response.InventoryTransactionTypeResponse;
import org.llin.demo.northwind.controller.response.InvoiceResponse;
import org.llin.demo.northwind.controller.response.OrderDetailResponse;
import org.llin.demo.northwind.controller.response.OrderDetailStatusResponse;
import org.llin.demo.northwind.controller.response.OrderStatusResponse;
import org.llin.demo.northwind.controller.response.OrderTaxStatusResponse;
import org.llin.demo.northwind.controller.response.PaymentTypeResponse;
import org.llin.demo.northwind.controller.response.PrivilegeResponse;
import org.llin.demo.northwind.controller.response.ProductResponse;
import org.llin.demo.northwind.controller.response.PurchaseOrderDetailResponse;
import org.llin.demo.northwind.controller.response.PurchaseOrderResponse;
import org.llin.demo.northwind.controller.response.PurchaseOrderStatusResponse;
import org.llin.demo.northwind.controller.response.RoleResponse;
import org.llin.demo.northwind.controller.response.ShipperResponse;
import org.llin.demo.northwind.controller.response.SupplierResponse;
import org.llin.demo.northwind.controller.response.TypeStateResponse;
import org.springframework.stereotype.Component;

@Component
public class ResponseCacheType<T extends BaseResponse> implements _Classes {
	
	private Map<String, Class<T>> responseTypes = new HashMap<>();

	public Map<String, Class<T>> getResponseTypes() {
		return responseTypes;
	}

	public void setResponseTypes(Map<String, Class<T>> responseTypes) {
		this.responseTypes = responseTypes;
	}
	
	@SuppressWarnings("unchecked")
	@PostConstruct
	public void init() {		
		responseTypes.put(AUTHENTICATION, (Class<T>) (new AuthenticationResponse()).getClass());
		responseTypes.put(COMPANY, (Class<T>) (new CompanyResponse()).getClass());
		responseTypes.put(CUSTOMER, (Class<T>) (new CustomerResponse()).getClass());
		responseTypes.put(CUSTOMER_ORDER, (Class<T>) (new CustomerOrderResponse()).getClass());		
		responseTypes.put(EMPLOYEE, (Class<T>) (new EmployeeResponse()).getClass());
		responseTypes.put(INVENTORY_TRANSACTION, (Class<T>) (new InventoryTransactionResponse()).getClass());
		responseTypes.put(INVENTORY_TRANSACTION_TYPE, (Class<T>) (new InventoryTransactionTypeResponse()).getClass());		
		responseTypes.put(INVOICE, (Class<T>) (new InvoiceResponse()).getClass());
		responseTypes.put(ORDER_DETAIL, (Class<T>) (new OrderDetailResponse()).getClass());		
		responseTypes.put(ORDER_DETAIL_STATUS, (Class<T>) (new OrderDetailStatusResponse()).getClass());
		responseTypes.put(ORDER_STATUS, (Class<T>) (new OrderStatusResponse()).getClass());
		responseTypes.put(ORDER_TAX_STATUS, (Class<T>) (new OrderTaxStatusResponse()).getClass());
		responseTypes.put(PAYMENT_TYPE, (Class<T>) (new PaymentTypeResponse()).getClass());
		responseTypes.put(PRIVILEGE, (Class<T>) (new PrivilegeResponse()).getClass());
		responseTypes.put(PRODUCT, (Class<T>) (new ProductResponse()).getClass());
		responseTypes.put(PURCHASE_ORDER, (Class<T>) (new PurchaseOrderResponse()).getClass());
		responseTypes.put(PURCHASE_ORDER_DETAIL, (Class<T>) (new PurchaseOrderDetailResponse()).getClass());
		responseTypes.put(PURCHASE_ORDER_STATUS, (Class<T>) (new PurchaseOrderStatusResponse()).getClass());		
		responseTypes.put(ROLE, (Class<T>) (new RoleResponse()).getClass());
		responseTypes.put(SHIPPER, (Class<T>) (new ShipperResponse()).getClass());
		responseTypes.put(SUPPLIER, (Class<T>) (new SupplierResponse()).getClass());
		responseTypes.put(TYPE_STATE, (Class<T>) (new TypeStateResponse()).getClass());			
	}
	
}
