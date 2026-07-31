package org.llin.demo.northwind.controller.entity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.llin.demo.northwind._Classes_CustomObject;
import org.llin.demo.northwind._Classes_EntityObject;
import org.llin.demo.northwind._Titles;
import org.llin.demo.northwind.dto.LabelValueLongDto;
import org.llin.demo.northwind.dto.LabelValueLongValueDoubleDto;
import org.llin.demo.northwind.dto.ProductDto;
import org.llin.demo.northwind.model.entity.EntityObject;
import org.llin.demo.northwind.service.entity.ProductService;
import org.llin.demo.northwind.service.entity.SupplierService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/product")
public class ProductController<T extends EntityObject> extends _EntityController<T>
		implements _Classes_CustomObject, _Classes_EntityObject, _Titles {

	private final ProductService productService;
	private final SupplierService supplierService;

	@Autowired
	public ProductController(ProductService productService, SupplierService supplierService) {
		this.productService = productService;
		this.supplierService = supplierService;
	}

	@GetMapping("/list")
	public ModelAndView getAllProducts() {
		return createDefaultModelAndView();
	}

	@PostMapping
	public ResponseEntity<ProductDto> create(@RequestBody ProductDto dto) {
		return ResponseEntity.ok(productService.create(dto));
	}

	@PutMapping
	public ResponseEntity<ProductDto> update(@RequestBody ProductDto dto) {
		return ResponseEntity.ok(productService.update(dto.id(), dto));
	}

	@DeleteMapping
	public ResponseEntity<ProductDto> delete(@RequestBody ProductDto dto) {
		productService.deleteById(dto.id());
		return ResponseEntity.ok(dto);
	}

	@GetMapping("/{id}")
	public ModelAndView findById(@PathVariable Integer id) {
		ModelAndView mav = createDefaultModelAndView();
		Optional<ProductDto> opt = productService.findById(id);

		if (opt.isPresent()) {
			mav.addObject(PRODUCT, opt.get());
			mav.setViewName("products/detail");
		} else {
			mav.setViewName("error/404");
		}

		return mav;
	}

	@GetMapping("/search/analytics/categoryRatios")
	public ModelAndView categoryRatios() {
		ModelAndView mv = createDefaultModelAndView();
		List<LabelValueLongValueDoubleDto> analytics = productService.categoryRatios();
		mv.addObject(KEY_PRODUCT_CATEGORY_RATIOS, analytics);
		return mv;
	}

	@GetMapping("/search/analytics/priceRangePerListPrice")
	public ModelAndView priceRangePerListPrice() {
		ModelAndView mv = createDefaultModelAndView();
		List<LabelValueLongDto> analytics = productService.priceRangePerListPrice();
		mv.addObject(KEY_PRODUCT_PRICE_RANGE_PER_LIST_PRICE, analytics);
		return mv;
	}

	@GetMapping("/search/analytics/priceRangePerStandardCost")
	public ModelAndView priceRangePerStandardCost() {
		ModelAndView mv = createDefaultModelAndView();
		List<LabelValueLongDto> analytics = productService.priceRangePerStandardCost();
		mv.addObject(KEY_PRODUCT_PRICE_RANGE_PER_STANDARD_COST, analytics);
		return mv;
	}

	@GetMapping("/search/findByProductCode")
	public ModelAndView findByCustomerOrderId(@RequestParam String productCode) {
		ModelAndView mv = createDefaultModelAndView();
		List<ProductDto> products = productService.findByProductCode(productCode);
		mv.addObject(PRODUCTS, products);
		return mv;
	}

	@GetMapping("/search/findByProductNameContaining")
	public ModelAndView findByProductNameContaining(@RequestParam String productName) {
		ModelAndView mv = createDefaultModelAndView();
		List<ProductDto> products = productService.findByProductNameContaining(productName);
		mv.addObject(PRODUCTS, products);
		return mv;
	}

	@GetMapping("/search/findByCategoryContaining")
	public ModelAndView findByCategoryContaining(@RequestParam String category) {
		ModelAndView mv = createDefaultModelAndView();
		List<ProductDto> products = productService.findByCategoryContaining(category);
		mv.addObject(PRODUCTS, products);
		return mv;
	}

	@GetMapping("/search/findByDescriptionContaining")
	public ModelAndView findByDescriptionContaining(@RequestParam String description) {
		ModelAndView mv = createDefaultModelAndView();
		List<ProductDto> products = productService.findByDescriptionContaining(description);
		mv.addObject(PRODUCTS, products);
		return mv;
	}

	@GetMapping("/search/findByDiscontinued")
	public ModelAndView findByDiscontinued(@RequestParam Boolean discontinued) {
		ModelAndView mv = createDefaultModelAndView();
		List<ProductDto> products = productService.findByDiscontinued(discontinued);
		mv.addObject(PRODUCTS, products);
		return mv;
	}

	@GetMapping("/search/findByStandardCostBetweenOrderByStandardCostAsc")
	public ModelAndView findByStandardCostBetweenOrderByStandardCostAsc(@RequestParam BigDecimal min,
			@RequestParam BigDecimal max) {

		ModelAndView mv = createDefaultModelAndView();

		List<ProductDto> products = productService.findByStandardCostBetweenOrderByStandardCostAsc(min, max);

		mv.addObject(PRODUCTS, products);
		return mv;
	}

	@GetMapping("/search/findByListPriceBetweenOrderByListPriceAsc")
	public ModelAndView findByListPriceBetweenOrderByListPriceAsc(@RequestParam BigDecimal min,
			@RequestParam BigDecimal max) {

		ModelAndView mv = createDefaultModelAndView();

		List<ProductDto> products = productService.findByListPriceBetweenOrderByListPriceAsc(min, max);

		mv.addObject(PRODUCTS, products);
		return mv;
	}

	@GetMapping("/search/findByReorderLevelBetweenOrderByReorderLevelAsc")
	public ModelAndView findByReorderLevelBetweenOrderByReorderLevelAsc(@RequestParam Integer min,
			@RequestParam Integer max) {

		ModelAndView mv = createDefaultModelAndView();

		List<ProductDto> products = productService.findByReorderLevelBetweenOrderByReorderLevelAsc(min, max);

		mv.addObject(PRODUCTS, products);
		return mv;
	}

	@GetMapping("/search/findByTargetLevelBetweenOrderByTargetLevelAsc")
	public ModelAndView findByTargetLevelBetweenOrderByTargetLevelAsc(@RequestParam Integer min,
			@RequestParam Integer max) {

		ModelAndView mv = createDefaultModelAndView();

		List<ProductDto> products = productService.findByTargetLevelBetweenOrderByTargetLevelAsc(min, max);

		mv.addObject(PRODUCTS, products);
		return mv;
	}

	@GetMapping("/search/findByMinimumReorderQuantityBetweenOrderByMinimumReorderQuantityAsc")
	public ModelAndView findByMinimumReorderQuantityBetweenOrderByMinimumReorderQuantityAsc(@RequestParam Integer min,
			@RequestParam Integer max) {

		ModelAndView mv = createDefaultModelAndView();

		List<ProductDto> products = productService
				.findByMinimumReorderQuantityBetweenOrderByMinimumReorderQuantityAsc(min, max);

		mv.addObject(PRODUCTS, products);
		return mv;
	}

	@GetMapping("/search/findByCategoryAndDiscontinued")
	public ModelAndView findByCategoryAndDiscontinued(@RequestParam String category,
			@RequestParam Boolean discontinued) {

		ModelAndView mv = createDefaultModelAndView();

		List<ProductDto> products = productService
				.findByCategoryAndDiscontinued(category, discontinued);

		mv.addObject(PRODUCTS, products);
		return mv;
	}
	
	private ModelAndView createDefaultModelAndView() {
		loadMenu();
		ModelAndView mv = new ModelAndView(getModelAndView().getView());
		mv.addObject(PRODUCTS, productService.findAll());
		mv.addObject(SUPPLIERS, supplierService.findAll());
		mv.addObject(TITLE, TITLE_PRODUCT);
		mv.setViewName("entities/product");
		return mv;
	}
}
