package org.llin.demo.northwind.controller.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.llin.demo.northwind._Classes_EntityObject;
import org.llin.demo.northwind._Titles;
import org.llin.demo.northwind.dto.OrderDetailDto;
import org.llin.demo.northwind.model.entity.EntityObject;
import org.llin.demo.northwind.service.entity.CustomerOrderService;
import org.llin.demo.northwind.service.entity.InventoryTransactionService;
import org.llin.demo.northwind.service.entity.InventoryTransactionTypeService;
import org.llin.demo.northwind.service.entity.OrderDetailService;
import org.llin.demo.northwind.service.entity.OrderStatusService;
import org.llin.demo.northwind.service.entity.ProductService;
import org.llin.demo.northwind.service.entity.PurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/orderDetail")
public class OrderDetailController<T extends EntityObject> extends _EntityController<T>
		implements _Classes_EntityObject, _Titles {

	private final OrderDetailService orderDetailService;
	private final CustomerOrderService customerOrderService;
	private final InventoryTransactionService inventoryTransactionService;
	private final InventoryTransactionTypeService inventoryTransactionTypeService;
	private final OrderStatusService orderStatusService;
	private final ProductService productService;
	private final PurchaseOrderService purchaseOrderService;

	@Autowired
	public OrderDetailController(OrderDetailService orderDetailService, CustomerOrderService customerOrderService,
			InventoryTransactionService inventoryTransactionService,
			InventoryTransactionTypeService inventoryTransactionTypeService, OrderStatusService orderStatusService,
			ProductService productService, PurchaseOrderService purchaseOrderService) {
		this.orderDetailService = orderDetailService;
		this.customerOrderService = customerOrderService;
		this.inventoryTransactionService = inventoryTransactionService;
		this.inventoryTransactionTypeService = inventoryTransactionTypeService;
		this.orderStatusService = orderStatusService;
		this.productService = productService;
		this.purchaseOrderService = purchaseOrderService;
	}

	@GetMapping("/list")
	public ModelAndView getAllOrderDetails() {
		return createDefaultModelAndView();
	}

	@PostMapping
	public ResponseEntity<OrderDetailDto> create(@RequestBody OrderDetailDto dto) {
		return ResponseEntity.ok(orderDetailService.create(dto));
	}

	@PutMapping
	public ResponseEntity<OrderDetailDto> update(@RequestBody OrderDetailDto dto) {
		return ResponseEntity.ok(orderDetailService.update(dto.id(), dto));
	}

	@DeleteMapping
	public ResponseEntity<OrderDetailDto> delete(@RequestBody OrderDetailDto dto) {
		orderDetailService.deleteById(dto.id());
		return ResponseEntity.ok(dto);
	}

	@GetMapping("/{id}")
	public ModelAndView findById(@PathVariable Integer id) {
		ModelAndView mav = createDefaultModelAndView();
		Optional<OrderDetailDto> orderDetailOpt = orderDetailService.findById(id);

		if (orderDetailOpt.isPresent()) {
			mav.addObject(ORDER_DETAIL, orderDetailOpt.get());
			mav.setViewName("orderDetails/detail");
		} else {
			mav.setViewName("error/404");
		}

		return mav;
	}

	@GetMapping("/search/findByCustomerOrderId")
	public ModelAndView findByCustomerOrderId(@RequestParam Integer id) {
		ModelAndView mv = createDefaultModelAndView();
		List<OrderDetailDto> orderDetails = orderDetailService.findByCustomerOrderId(id);
		mv.addObject(ORDER_DETAILS, orderDetails);
		return mv;
	}

	@GetMapping("/search/findByProductId")
	public ModelAndView findByProductId(@RequestParam Integer id) {
		ModelAndView mv = createDefaultModelAndView();
		List<OrderDetailDto> orderDetails = orderDetailService.findByProductId(id);
		mv.addObject(ORDER_DETAILS, orderDetails);
		return mv;
	}

	@GetMapping("/search/findByOrderStatusId")
	public ModelAndView findByOrderStatusId(@RequestParam Integer id) {
		ModelAndView mv = createDefaultModelAndView();
		List<OrderDetailDto> orderDetails = orderDetailService.findByOrderStatusId(id);
		mv.addObject(ORDER_DETAILS, orderDetails);
		return mv;
	}

	@GetMapping("/search/findByPurchaseOrderId")
	public ModelAndView findByPurchaseOrderId(@RequestParam Integer id) {
		ModelAndView mv = createDefaultModelAndView();
		List<OrderDetailDto> orderDetails = orderDetailService.findByPurchaseOrderId(id);
		mv.addObject(ORDER_DETAILS, orderDetails);
		return mv;
	}

	@GetMapping("/search/findByInventoryTransactionId")
	public ModelAndView findByInventoryTransactionId(@RequestParam Integer id) {
		ModelAndView mv = createDefaultModelAndView();
		List<OrderDetailDto> orderDetails = orderDetailService.findByInventoryTransactionId(id);
		mv.addObject(ORDER_DETAILS, orderDetails);
		return mv;
	}

	@GetMapping("/search/findByQuantityBetweenOrderByQuantityAsc")
	public ModelAndView findByQuantityBetweenOrderByQuantityAsc(@RequestParam BigDecimal min,
			@RequestParam BigDecimal max) {

		ModelAndView mv = createDefaultModelAndView();

		List<OrderDetailDto> orderDetails = orderDetailService.findByQuantityBetweenOrderByQuantityAsc(min, max);

		mv.addObject(ORDER_DETAILS, orderDetails);
		return mv;
	}

	@GetMapping("/search/findByUnitPriceBetweenOrderByUnitPriceAsc")
	public ModelAndView findByUnitPriceBetweenOrderByUnitPriceAsc(@RequestParam BigDecimal min,
			@RequestParam BigDecimal max) {

		ModelAndView mv = createDefaultModelAndView();

		List<OrderDetailDto> orderDetails = orderDetailService.findByUnitPriceBetweenOrderByUnitPriceAsc(min, max);

		mv.addObject(ORDER_DETAILS, orderDetails);
		return mv;
	}

	@GetMapping("/search/findByDiscountBetweenOrderByDiscountAsc")
	public ModelAndView findByDiscountBetweenOrderByDiscountAsc(@RequestParam Double min, @RequestParam Double max) {

		ModelAndView mv = createDefaultModelAndView();

		List<OrderDetailDto> orderDetails = orderDetailService.findByDiscountBetweenOrderByDiscountAsc(min, max);

		mv.addObject(ORDER_DETAILS, orderDetails);
		return mv;
	}

	@GetMapping("/search/findByDateAllocatedBetweenOrderByDateAllocatedAsc")
	public ModelAndView findByDateAllocatedBetweenOrderByDateAllocatedAsc(
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {

		ModelAndView mv = createDefaultModelAndView();

		List<OrderDetailDto> orderDetails = orderDetailService.findByDateAllocatedBetweenOrderByDateAllocatedAsc(start,
				end);

		mv.addObject(ORDER_DETAILS, orderDetails);
		return mv;
	}

	private ModelAndView createDefaultModelAndView() {
		loadMenu();
		ModelAndView mv = new ModelAndView(getModelAndView().getView());
		mv.addObject(ORDER_DETAILS, orderDetailService.findAll());
		mv.addObject(CUSTOMER_ORDERS, customerOrderService.findAll());
		mv.addObject(INVENTORY_TRANSACTIONS, inventoryTransactionService.findAll());
		mv.addObject(INVENTORY_TRANSACTION_TYPES, inventoryTransactionTypeService.findAll());
		mv.addObject(ORDER_STATUSES, orderStatusService.findAll());
		mv.addObject(PRODUCTS, productService.findAll());
		mv.addObject(PURCHASE_ORDERS, purchaseOrderService.findAll());
		mv.addObject(TITLE, TITLE_ORDER_DETAIL);
		mv.setViewName("entities/orderDetail");
		return mv;
	}

}
