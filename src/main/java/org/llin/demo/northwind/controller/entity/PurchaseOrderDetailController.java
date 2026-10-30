package org.llin.demo.northwind.controller.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.llin.demo.northwind._Classes_CustomObject;
import org.llin.demo.northwind._Classes_EntityObject;
import org.llin.demo.northwind._Titles;
import org.llin.demo.northwind.dto.PurchaseOrderDetailDto;
import org.llin.demo.northwind.model.entity._EntityObject;
import org.llin.demo.northwind.service.entity.PurchaseOrderDetailService;
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
@RequestMapping("/entity/purchaseOrderDetail")
public class PurchaseOrderDetailController<T extends _EntityObject> extends _EntityController<T>
		implements _Classes_CustomObject, _Classes_EntityObject, _Titles {

	private final PurchaseOrderDetailService purchaseOrderDetailService;

	@Autowired
	public PurchaseOrderDetailController(PurchaseOrderDetailService purchaseOrderDetailService) {
		this.purchaseOrderDetailService = purchaseOrderDetailService;
	}

	@GetMapping("/list")
	public ModelAndView getAllPurchaseOrderDetails() {
		return createDefaultModelAndView();
	}

	@PostMapping
	public ResponseEntity<PurchaseOrderDetailDto> create(@RequestBody PurchaseOrderDetailDto dto) {
		return ResponseEntity.ok(purchaseOrderDetailService.create(dto));
	}

	@PutMapping
	public ResponseEntity<PurchaseOrderDetailDto> update(@RequestBody PurchaseOrderDetailDto dto) {
		return ResponseEntity.ok(purchaseOrderDetailService.update(dto.id(), dto));
	}

	@DeleteMapping
	public ResponseEntity<PurchaseOrderDetailDto> delete(@RequestBody PurchaseOrderDetailDto dto) {
		purchaseOrderDetailService.deleteById(dto.id());
		return ResponseEntity.ok(dto);
	}

	@GetMapping("/{id}")
	public ModelAndView findById(@PathVariable Integer id) {
		ModelAndView mav = createDefaultModelAndView();
		Optional<PurchaseOrderDetailDto> opt = purchaseOrderDetailService.findById(id);

		if (opt.isPresent()) {
			mav.addObject(PURCHASE_ORDER_DETAIL, opt.get());
			mav.setViewName("purchaseOrderDetails/detail");
		} else {
			mav.setViewName("error/404");
		}

		return mav;
	}

	@GetMapping("/search/findByPurchaseOrderId")
	public ModelAndView findByPurchaseOrderId(@RequestParam Integer id) {
		ModelAndView mv = createDefaultModelAndView();
		List<PurchaseOrderDetailDto> purchaseOrderDetails = purchaseOrderDetailService.findByPurchaseOrderId(id);
		mv.addObject(PURCHASE_ORDER_DETAILS, enrichAll(purchaseOrderDetails));
		return mv;
	}

	@GetMapping("/search/findByProductId")
	public ModelAndView findByProductId(@RequestParam Integer id) {
		ModelAndView mv = createDefaultModelAndView();
		List<PurchaseOrderDetailDto> purchaseOrderDetails = purchaseOrderDetailService.findByProductId(id);
		mv.addObject(PURCHASE_ORDER_DETAILS, enrichAll(purchaseOrderDetails));
		return mv;
	}

	@GetMapping("/search/findByInventoryTransactionId")
	public ModelAndView findByInventoryTransactionId(@RequestParam Integer id) {
		ModelAndView mv = createDefaultModelAndView();
		List<PurchaseOrderDetailDto> purchaseOrderDetails = purchaseOrderDetailService.findByInventoryTransactionId(id);
		mv.addObject(PURCHASE_ORDER_DETAILS, enrichAll(purchaseOrderDetails));
		return mv;
	}

	@GetMapping("/search/findByQuantityBetweenOrderByQuantityAsc")
	public ModelAndView findByQuantityBetweenOrderByQuantityAsc(@RequestParam BigDecimal min,
			@RequestParam BigDecimal max) {

		ModelAndView mv = createDefaultModelAndView();

		List<PurchaseOrderDetailDto> purchaseOrderDetails = purchaseOrderDetailService
				.findByQuantityBetweenOrderByQuantityAsc(min, max);

		mv.addObject(PURCHASE_ORDER_DETAILS, enrichAll(purchaseOrderDetails));
		return mv;
	}

	@GetMapping("/search/findByUnitCostBetweenOrderByUnitCostAsc")
	public ModelAndView findByUnitCostBetweenOrderByUnitCostAsc(@RequestParam BigDecimal min,
			@RequestParam BigDecimal max) {

		ModelAndView mv = createDefaultModelAndView();

		List<PurchaseOrderDetailDto> purchaseOrderDetails = purchaseOrderDetailService
				.findByUnitCostBetweenOrderByUnitCostAsc(min, max);

		mv.addObject(PURCHASE_ORDER_DETAILS, enrichAll(purchaseOrderDetails));
		return mv;
	}

	@GetMapping("/search/findByDateReceivedBetweenOrderByDateReceivedAsc")
	public ModelAndView findByDateReceivedBetweenOrderByDateReceivedAsc(
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {

		ModelAndView mv = createDefaultModelAndView();

		List<PurchaseOrderDetailDto> purchaseOrderDetails = purchaseOrderDetailService
				.findByDateReceivedBetweenOrderByDateReceivedAsc(start, end);

		mv.addObject(PURCHASE_ORDER_DETAILS, enrichAll(purchaseOrderDetails));
		return mv;
	}

	@GetMapping("/search/findByPostedToInventory")
	public ModelAndView findByPostedToInventory(@RequestParam Boolean postedToInventory) {
		ModelAndView mv = createDefaultModelAndView();
		List<PurchaseOrderDetailDto> purchaseOrderDetails = purchaseOrderDetailService
				.findByPostedToInventory(postedToInventory);
		mv.addObject(PURCHASE_ORDER_DETAILS, enrichAll(purchaseOrderDetails));
		return mv;
	}

	private ModelAndView createDefaultModelAndView() {
		loadMenu();
		ModelAndView mv = new ModelAndView(getModelAndView().getView());
		mv.addObject(PURCHASE_ORDER_DETAILS, enrichAll(purchaseOrderDetailService.findAll()));
		mv.addObject("activeMenuSection", "fourthOrder");
		mv.addObject("currentEntityPath", "purchaseOrderDetail");
		mv.addObject(TITLE, TITLE_PURCHASE_ORDER_DETAIL);
		mv.setViewName("entity/purchaseOrderDetail");
		return mv;
	}

	private List<PurchaseOrderDetailDto> enrichAll(List<PurchaseOrderDetailDto> purchaseOrderDetails) {

		if (purchaseOrderDetails == null || purchaseOrderDetails.isEmpty())
			return List.of();
		return purchaseOrderDetails.stream().map(purchaseOrderDetail -> {
			try {
				return purchaseOrderDetailService.enrich(purchaseOrderDetail);
			} catch (Exception e) {
				// Force a print statements to capture what Eclipse hides
				System.err.println(
						"CRITICAL: Failed to enrich ID " + purchaseOrderDetail.id() + " -> " + e.getMessage());
				return purchaseOrderDetail; // Return raw, un-enriched DTO so the page still loads!
			}
		}).toList();
	}
	
}
