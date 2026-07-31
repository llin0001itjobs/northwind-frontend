package org.llin.demo.northwind.controller.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.llin.demo.northwind._Classes_EntityObject;
import org.llin.demo.northwind._Titles;
import org.llin.demo.northwind.dto.PurchaseOrderDto;
import org.llin.demo.northwind.model.entity.EntityObject;
import org.llin.demo.northwind.service.entity.EmployeeService;
import org.llin.demo.northwind.service.entity.OrderStatusService;
import org.llin.demo.northwind.service.entity.PurchaseOrderService;
import org.llin.demo.northwind.service.entity.SupplierService;
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
@RequestMapping("/purchaseOrder")
public class PurchaseOrderController<T extends EntityObject> extends _EntityController<T>
		implements _Classes_EntityObject, _Titles {

	private final PurchaseOrderService purchaseOrderService;
	private final EmployeeService employeeService;
	private final SupplierService supplierService;
	private final OrderStatusService orderStatusService;

	@Autowired
	public PurchaseOrderController(PurchaseOrderService purchaseOrderService, EmployeeService employeeService,
			SupplierService supplierService, OrderStatusService orderStatusService) {
		this.purchaseOrderService = purchaseOrderService;
		this.employeeService = employeeService;
		this.supplierService = supplierService;
		this.orderStatusService = orderStatusService;
	}

	@GetMapping("/list")
	public ModelAndView getAllPurchaseOrders() {
		return createDefaultModelAndView();
	}

	@PostMapping
	public ResponseEntity<PurchaseOrderDto> create(@RequestBody PurchaseOrderDto dto) {
		return ResponseEntity.ok(purchaseOrderService.create(dto));
	}

	@PutMapping
	public ResponseEntity<PurchaseOrderDto> update(@RequestBody PurchaseOrderDto dto) {
		return ResponseEntity.ok(purchaseOrderService.update(dto.id(), dto));
	}

	@DeleteMapping
	public ResponseEntity<PurchaseOrderDto> delete(@RequestBody PurchaseOrderDto dto) {
		purchaseOrderService.deleteById(dto.id());
		return ResponseEntity.ok(dto);
	}

	@GetMapping("/{id}")
	public ModelAndView findById(@PathVariable Integer id) {
		ModelAndView mav = createDefaultModelAndView();
		Optional<PurchaseOrderDto> opt = purchaseOrderService.findById(id);

		if (opt.isPresent()) {
			mav.addObject(PURCHASE_ORDER, opt.get());
			mav.setViewName("purchaseOrders/detail");
		} else {
			mav.setViewName("error/404");
		}

		return mav;
	}

	@GetMapping("/search/findBySupplierId")
	public ModelAndView findBySupplierId(@RequestParam Integer id) {
		ModelAndView mv = createDefaultModelAndView();
		List<PurchaseOrderDto> purchaseOrders = purchaseOrderService.findBySupplierId(id);
		mv.addObject(PURCHASE_ORDERS, purchaseOrders);
		return mv;
	}

	@GetMapping("/search/findByCreatedById")
	public ModelAndView findByCreatedById(@RequestParam Integer id) {
		ModelAndView mv = createDefaultModelAndView();
		List<PurchaseOrderDto> purchaseOrders = purchaseOrderService.findByCreatedById(id);
		mv.addObject(PURCHASE_ORDERS, purchaseOrders);
		return mv;
	}

	@GetMapping("/search/findByApprovedById")
	public ModelAndView findByApprovedById(@RequestParam Integer id) {
		ModelAndView mv = createDefaultModelAndView();
		List<PurchaseOrderDto> purchaseOrders = purchaseOrderService.findByApprovedById(id);
		mv.addObject(PURCHASE_ORDERS, purchaseOrders);
		return mv;
	}

	@GetMapping("/search/findBySubmittedById")
	public ModelAndView findBySubmittedById(@RequestParam Integer id) {
		ModelAndView mv = createDefaultModelAndView();
		List<PurchaseOrderDto> purchaseOrders = purchaseOrderService.findBySubmittedById(id);
		mv.addObject(PURCHASE_ORDERS, purchaseOrders);
		return mv;
	}

	@GetMapping("/search/findByOrderStatusId")
	public ModelAndView findByOrderStatusId(@RequestParam Integer id) {
		ModelAndView mv = createDefaultModelAndView();
		List<PurchaseOrderDto> purchaseOrders = purchaseOrderService.findByOrderStatusId(id);
		mv.addObject(PURCHASE_ORDERS, purchaseOrders);
		return mv;
	}

	@GetMapping("/search/findByCreationDateBetweenOrderByCreationDateAsc")
	public ModelAndView findByCreationDateBetweenOrderByCreationDateAsc(
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {

		ModelAndView mv = createDefaultModelAndView();

		List<PurchaseOrderDto> purchaseOrders = purchaseOrderService
				.findByCreationDateBetweenOrderByCreationDateAsc(start, end);

		mv.addObject(PURCHASE_ORDERS, purchaseOrders);
		return mv;
	}

	@GetMapping("/search/findBySubmittedDateBetweenOrderBySubmittedDateAsc")
	public ModelAndView findBySubmittedDateBetweenOrderBySubmittedDateAsc(
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {

		ModelAndView mv = createDefaultModelAndView();

		List<PurchaseOrderDto> purchaseOrders = purchaseOrderService
				.findBySubmittedDateBetweenOrderBySubmittedDateAsc(start, end);

		mv.addObject(PURCHASE_ORDERS, purchaseOrders);
		return mv;
	}

	@GetMapping("/search/findByExpectedDateBetweenOrderByExpectedDateAsc")
	public ModelAndView findByExpectedDateBetweenOrderByExpectedDateAsc(
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {

		ModelAndView mv = createDefaultModelAndView();

		List<PurchaseOrderDto> purchaseOrders = purchaseOrderService
				.findByExpectedDateBetweenOrderByExpectedDateAsc(start, end);

		mv.addObject(PURCHASE_ORDERS, purchaseOrders);
		return mv;
	}

	@GetMapping("/search/findByPaymentDateBetweenOrderByPaymentDateAsc")
	public ModelAndView findByPaymentDateBetweenOrderByPaymentDateAsc(
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {

		ModelAndView mv = createDefaultModelAndView();

		List<PurchaseOrderDto> purchaseOrders = purchaseOrderService
				.findByPaymentDateBetweenOrderByPaymentDateAsc(start, end);

		mv.addObject(PURCHASE_ORDERS, purchaseOrders);
		return mv;
	}

	@GetMapping("/search/findByShippingFeeBetweenOrderByShippingFeeAsc")
	public ModelAndView findByShippingFeeBetweenOrderByShippingFeeAsc(@RequestParam BigDecimal min,
			@RequestParam BigDecimal max) {

		ModelAndView mv = createDefaultModelAndView();

		List<PurchaseOrderDto> purchaseOrders = purchaseOrderService.findByShippingFeeBetweenOrderByShippingFeeAsc(min,
				max);

		mv.addObject(PURCHASE_ORDERS, purchaseOrders);
		return mv;
	}

	@GetMapping("/search/findByTaxesBetweenOrderByTaxesAsc")
	public ModelAndView findByTaxesBetweenOrderByTaxesAsc(@RequestParam BigDecimal min, @RequestParam BigDecimal max) {

		ModelAndView mv = createDefaultModelAndView();

		List<PurchaseOrderDto> purchaseOrders = purchaseOrderService.findByTaxesBetweenOrderByTaxesAsc(min, max);

		mv.addObject(PURCHASE_ORDERS, purchaseOrders);
		return mv;
	}

	@GetMapping("/search/findByPaymentAmountBetweenOrderByPaymentAmountAsc")
	public ModelAndView findByPaymentAmountBetweenOrderByPaymentAmountAsc(@RequestParam BigDecimal min,
			@RequestParam BigDecimal max) {

		ModelAndView mv = createDefaultModelAndView();

		List<PurchaseOrderDto> purchaseOrders = purchaseOrderService
				.findByPaymentAmountBetweenOrderByPaymentAmountAsc(min, max);

		mv.addObject(PURCHASE_ORDERS, purchaseOrders);
		return mv;
	}

	@GetMapping("/search/findByNotesContaining")
	public ModelAndView findByNotesContaining(@RequestParam String notes) {
		ModelAndView mv = createDefaultModelAndView();
		List<PurchaseOrderDto> purchaseOrders = purchaseOrderService.findByNotesContaining(notes);
		mv.addObject(PURCHASE_ORDERS, purchaseOrders);
		return mv;
	}

	private ModelAndView createDefaultModelAndView() {
		loadMenu();
		ModelAndView mv = new ModelAndView(getModelAndView().getView());
		mv.addObject(PURCHASE_ORDERS, purchaseOrderService.findAll());
		mv.addObject(EMPLOYEES, employeeService.findAll());
		mv.addObject(SUPPLIERS, supplierService.findAll());
		mv.addObject(ORDER_STATUSES, orderStatusService.findAll());
		mv.addObject(TITLE, TITLE_PURCHASE_ORDER);
		mv.setViewName("entities/purchaseOrder");
		return mv;
	}

}
