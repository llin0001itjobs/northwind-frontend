package org.llin.demo.northwind.controller.reports;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.llin.demo.northwind.service.entity.CustomerOrderService;
import org.llin.demo.northwind.service.entity.ProductService;
import org.llin.demo.northwind.service.entity.PurchaseOrderDetailService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/reports")
@RequiredArgsConstructor
public class AnalyticsReportController {

    private final CustomerOrderService customerOrderService;
    private final ProductService productService;
    private final PurchaseOrderDetailService purchaseOrderDetailService;

    @GetMapping("/analytics")
    public String showAnalytics(Model model) {

        // === Fetch all data server-side ===
        Map<String, Object> matrixAnalytics = new HashMap<>();
        Map<String, List<String>> matrixAnalyticsTitles = new HashMap<>(); // optional for future use

        // ── Customer Order ─────────────────────────────────────
        Map<String, Object> mapCO = new HashMap<>();
        mapCO.put("orderCount", customerOrderService.orderCountPerMonth());
        mapCO.put("status", customerOrderService.ordersByStatus());
        mapCO.put("shippingFees", customerOrderService.shippingFeePerMonth());
        matrixAnalytics.put("customerOrder", mapCO);

        matrixAnalyticsTitles.put("customerOrder", List.of("Order Count", "Status", "Shipping Fees"));

        // ── Product ────────────────────────────────────────────
        Map<String, Object> mapProd = new HashMap<>();
        mapProd.put("categoryRatios", productService.categoryRatios());
        mapProd.put("priceRangeListPrice", productService.priceRangePerListPrice());
        mapProd.put("priceRangeStandardCost", productService.priceRangePerStandardCost());
        matrixAnalytics.put("product", mapProd);

        matrixAnalyticsTitles.put("product", List.of("Category Ratios", "Price Range (List Price)", "Price Range (Standard Cost)"));

        // ── Purchase Order Detail ──────────────────────────────
        Map<String, Object> mapPOD = new HashMap<>();
        mapPOD.put("quantityPerUnitCost", purchaseOrderDetailService.quantityPerUnitCost());
        mapPOD.put("shippingFees", purchaseOrderDetailService.shippingFeePerMonth());
        matrixAnalytics.put("purchaseOrderDetail", mapPOD);

        matrixAnalyticsTitles.put("purchaseOrderDetail", List.of("Quantity Per Unit Cost", "Shipping Fees"));

        model.addAttribute("matrixAnalytics", matrixAnalytics);
        model.addAttribute("matrixAnalyticsTitles", matrixAnalyticsTitles);
        model.addAttribute("ACTIVE_NAV_ITEM", "nav-item-reports");

        return "reports/analytics";   // your Thymeleaf template
    }
}