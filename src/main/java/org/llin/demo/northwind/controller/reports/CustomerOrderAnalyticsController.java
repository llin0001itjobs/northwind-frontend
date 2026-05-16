package org.llin.demo.northwind.controller.reports;

import java.util.List;

import org.llin.demo.northwind.dto.LabelIntValueDoubleDto;
import org.llin.demo.northwind.dto.LabelIntValueLongDto;
import org.llin.demo.northwind.dto.LabelValueLongDto;
import org.llin.demo.northwind.service.entity.CustomerOrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/analytics/customer-order")
@RequiredArgsConstructor
public class CustomerOrderAnalyticsController {

    private final CustomerOrderService service;

    @GetMapping("/fee-range-count")      public List<LabelValueLongDto> feeRangePerCount() { return service.feeRangePerCount(); }
    @GetMapping("/shipping-fee-month")   public List<LabelIntValueDoubleDto> shippingFeePerMonth() { return service.shippingFeePerMonth(); }
    @GetMapping("/order-count-month")    public List<LabelIntValueLongDto> orderCountPerMonth() { return service.orderCountPerMonth(); }
    @GetMapping("/by-status")            public List<LabelValueLongDto> ordersByStatus() { return service.ordersByStatus(); }

}
