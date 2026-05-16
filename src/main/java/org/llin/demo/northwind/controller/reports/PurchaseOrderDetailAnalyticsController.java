package org.llin.demo.northwind.controller.reports;

import java.util.List;

import org.llin.demo.northwind.dto.LabelDoubleValueLongDto;
import org.llin.demo.northwind.dto.LabelValueLongDto;
import org.llin.demo.northwind.service.entity.PurchaseOrderDetailService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/analytics/purchase-order-detail")
@RequiredArgsConstructor
public class PurchaseOrderDetailAnalyticsController {

	private final PurchaseOrderDetailService service;
	
    @GetMapping("/shipping-fee-month")   public List<LabelValueLongDto> shippingFeePerMonth() { return service.shippingFeePerMonth(); }
    @GetMapping("/order-count-month")    public List<LabelDoubleValueLongDto> orderCountPerMonth() { return service.quantityPerUnitCost(); }
  }
