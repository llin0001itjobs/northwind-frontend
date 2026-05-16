package org.llin.demo.northwind.controller.reports;

import java.util.List;

import org.llin.demo.northwind.dto.LabelValueLongValueDoubleDto;
import org.llin.demo.northwind.dto.LabelValueLongDto;
import org.llin.demo.northwind.service.entity.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/analytics/customer-order")
@RequiredArgsConstructor
public class ProductAnalyticsController {
	private final ProductService service;
			
    @GetMapping("/category-ratios")      public List<LabelValueLongValueDoubleDto> categoryRatios() { return service.categoryRatios(); }
    @GetMapping("/price-range-per-list-price")   public List<LabelValueLongDto> priceRangePerListPrice() { return service.priceRangePerListPrice(); }
    @GetMapping("/price-range-per-standard-cost")    public List<LabelValueLongDto> priceRangePerStandardCost() { return service.priceRangePerStandardCost(); }
	
}
