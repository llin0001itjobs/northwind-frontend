package org.llin.demo.northwind.controller.entity;

import java.util.Optional;

import org.llin.demo.northwind._Classes_CustomObject;
import org.llin.demo.northwind._Classes_EntityObject;
import org.llin.demo.northwind._Titles;
import org.llin.demo.northwind.dto.CustomerOrderDto;
import org.llin.demo.northwind.model.entity.EntityObject;
import org.llin.demo.northwind.service.entity.CustomerOrderService;
import org.llin.demo.northwind.service.entity.CustomerService;
import org.llin.demo.northwind.service.entity.EmployeeService;
import org.llin.demo.northwind.service.entity.OrderStatusService;
import org.llin.demo.northwind.service.entity.OrderTaxStatusService;
import org.llin.demo.northwind.service.entity.ShipperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/customerOrder")
public class CustomerOrderController<T extends EntityObject> extends _EntityController<T>
		implements _Classes_CustomObject, _Classes_EntityObject, _Titles {

	private final CustomerOrderService customerOrderService;
	private final CustomerService customerService;
	private final EmployeeService employeeService;
	private final ShipperService shipperService;
	private final OrderStatusService orderStatusService;
	private final OrderTaxStatusService orderTaxStatusService;

	@Autowired
	public CustomerOrderController(CustomerOrderService customerOrderService, CustomerService customerService,
			EmployeeService employeeService, ShipperService shipperService, OrderStatusService orderStatusService,
			OrderTaxStatusService orderTaxStatusService) {
		this.customerOrderService = customerOrderService;
		this.customerService = customerService;
		this.employeeService = employeeService;
		this.shipperService = shipperService;
		this.orderStatusService = orderStatusService;
		this.orderTaxStatusService = orderTaxStatusService;
	}

	@PostMapping
	public ResponseEntity<CustomerOrderDto> create(@RequestBody CustomerOrderDto dto) {
		return ResponseEntity.ok(customerOrderService.create(dto));
	}

	@PutMapping
	public ResponseEntity<CustomerOrderDto> update(@RequestBody CustomerOrderDto dto) {
		return ResponseEntity.ok(customerOrderService.update(dto.id(), dto));
	}

	@DeleteMapping
	public ResponseEntity<CustomerOrderDto> delete(@RequestBody CustomerOrderDto dto) {
		customerOrderService.deleteById(dto.id());
		return ResponseEntity.ok(dto);
	}

	@GetMapping("/list")
	public ModelAndView getAllCustomerOrders() {
		return createDefaultModelAndView();
	}
	
	@GetMapping("/{id}")
	public ModelAndView findById(@PathVariable Integer id) {
		ModelAndView mav = createDefaultModelAndView();
		Optional<CustomerOrderDto> customerOrderOpt = customerOrderService.findById(id);

		if (customerOrderOpt.isPresent()) {
			mav.addObject(CUSTOMER, customerOrderOpt.get()); // Extract the raw DTO
			mav.setViewName("customerOrders/detail");
		} else {
			mav.setViewName("error/404"); // Handle missing customer safely
		}
		return mav;
	}
	
	@GetMapping("/findByCustomerId")
	public ModelAndView findByCustomerId(@PathVariable Integer customerId) {
		ModelAndView mav = createDefaultModelAndView();
		mav.addObject(CUSTOMER_ORDERS, customerOrderService.findByCustomerId(customerId));
		return mav;		
	}
	
	@GetMapping("/findByCustomerIdOrderByOrderDateDesc")
	public ModelAndView findByCustomerIdOrderByOrderDateDesc(@PathVariable Integer customerId) {
		ModelAndView mav = createDefaultModelAndView();
		mav.addObject(CUSTOMER_ORDERS, customerOrderService.findByCustomerIdOrderByOrderDateDesc(customerId));
		return mav;
	}
	
	@GetMapping("/findByCustomerIdAndShipCityContaining")
	public ModelAndView findByCustomerIdAndShipCityContaining(@PathVariable Integer customerId, @PathVariable String shippingCity) {
		ModelAndView mav = createDefaultModelAndView();
		mav.addObject(CUSTOMER_ORDERS, customerOrderService.findByCustomerIdAndShipCityContaining(customerId, shippingCity));
		return mav;
	}

	@GetMapping("/findByEmployeeId")
	public ModelAndView findByEmployeeId(@PathVariable Integer employeeId) {
		ModelAndView mav = createDefaultModelAndView();
		mav.addObject(CUSTOMER_ORDERS, customerOrderService.findByEmployeeId(employeeId));
		return mav;
	}
	
	@GetMapping("/findByShipperId")
	public ModelAndView findByShipperId(@PathVariable Integer shipperId) {
		ModelAndView mav = createDefaultModelAndView();
		mav.addObject(CUSTOMER_ORDERS, customerOrderService.findByShipperId(shipperId));
		return mav;
	}
	
	@GetMapping("/findByOrderStatusId")
	public ModelAndView findByOrderStatusId(@PathVariable Integer orderStatusId) {
		ModelAndView mav = createDefaultModelAndView();
		mav.addObject(CUSTOMER_ORDERS, customerOrderService.findByOrderStatusId(orderStatusId));
		return mav;
	}
	
	@GetMapping("/findByOrderTaxStatusId")
	public ModelAndView findByOrderTaxStatusId(@PathVariable Integer orderTaxStatusId) {
		ModelAndView mav = createDefaultModelAndView();
		mav.addObject(CUSTOMER_ORDERS, customerOrderService.findByOrderTaxStatusId(orderTaxStatusId));
		return mav;
	}
	
	@GetMapping("/findByShipName")
	public ModelAndView findByShipName(@PathVariable String shippingName) {
		ModelAndView mav = createDefaultModelAndView();
		mav.addObject(CUSTOMER_ORDERS, customerOrderService.findByShipName(shippingName));
		return mav;
	}
	
	@GetMapping("/findByShipNameContaining")
	public ModelAndView findByShipNameContaining(@PathVariable String shippingName) {
		ModelAndView mav = createDefaultModelAndView();
		mav.addObject(CUSTOMER_ORDERS, customerOrderService.findByShipNameContaining(shippingName));
		return mav;
	}

	@GetMapping("/findByNotesContaining")
	public ModelAndView findByNotesContaining(@PathVariable String notes) {
		ModelAndView mav = createDefaultModelAndView();
		mav.addObject(CUSTOMER_ORDERS, customerOrderService.findByNotesContaining(notes));
		return mav;
	}
	
	@GetMapping("/findByPaymentType")
	public ModelAndView findByPaymentType(@PathVariable String paymentType) {
		ModelAndView mav = createDefaultModelAndView();
		mav.addObject(CUSTOMER_ORDERS, customerOrderService.findByPaymentType(paymentType));
		return mav;
	}
	
	@GetMapping("/findByShippedDateIsNull")
	public ModelAndView findByShippedDateIsNull() {
		ModelAndView mav = createDefaultModelAndView();
		mav.addObject(CUSTOMER_ORDERS, customerOrderService.findByShippedDateIsNull());
		return mav;
	}
	
	@GetMapping("/findByPaidDateIsNull")
	public ModelAndView findByPaidDateIsNull() {
		ModelAndView mav = createDefaultModelAndView();
		mav.addObject(CUSTOMER_ORDERS, customerOrderService.findByPaidDateIsNull());
		return mav;
	}
		
	@GetMapping("/analytics/by-status")
	public ModelAndView analyticsOrderFeeByStatus() {
		ModelAndView mav = createDefaultModelAndView();		
		mav.addObject(KEY_CUSTOMER_ORDER_FEE_BY_STATUS, customerOrderService.ordersByStatus());
		return mav;
	}
	
	@GetMapping("/analytics/fee-range-count")
	public ModelAndView analyticsFeeRangePerCount() {
		ModelAndView mav = createDefaultModelAndView();		
		mav.addObject(KEY_CUSTOMER_ORDER_FEE_RANGE_PER_COUNT, customerOrderService.feeRangePerCount());
		return mav;
	}

	@GetMapping("/analytics/order-count-month")
	public ModelAndView analyticsOrderCountPerMonth() {
		ModelAndView mav = createDefaultModelAndView();		
		mav.addObject(KEY_CUSTOMER_ORDER_ORDER_COUNT_MONTH, customerOrderService.orderCountPerMonth());
		return mav;
	}
	
	@GetMapping("/analytics/shipping-fee-month")
	public ModelAndView analyticsShippingFeeMonth() {
		ModelAndView mav = createDefaultModelAndView();		
		mav.addObject(KEY_CUSTOMER_ORDER_SHIPPING_FEE_PER_MONTH, customerOrderService.shippingFeePerMonth());
		return mav;
	}
	
	
	private ModelAndView createDefaultModelAndView() {
		loadMenu();
		ModelAndView mav = new ModelAndView(getModelAndView().getView());
		modelAndView.addObject(CUSTOMER_ORDERS, customerOrderService.findAll());
		modelAndView.addObject(CUSTOMERS, customerService.findAll());
		modelAndView.addObject(EMPLOYEES, employeeService.findAll());
		modelAndView.addObject(SHIPPERS, shipperService.findAll());
		modelAndView.addObject(ORDER_STATUSES, orderStatusService.findAll());
		modelAndView.addObject(ORDER_TAX_STATUSES, orderTaxStatusService.findAll());
		modelAndView.addObject(TITLE, TITLE_CUSTOMER_ORDER);
		modelAndView.setViewName("entities/customerOrder");
		return mav;
	}

}
