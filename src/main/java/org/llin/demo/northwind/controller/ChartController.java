package org.llin.demo.northwind.controller;

import org.llin.demo.northwind._Classes_CustomObject;
import org.llin.demo.northwind.cache.CustomSqlCache;
import org.llin.demo.northwind.model.charts.LabelValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/chart")
public class ChartController implements _Classes_CustomObject {

	private static final String ACTIVE_NAV_ITEM = "ACTIVE_NAV_ITEM";
	private static final String CHART_VIEW = "fragments/chart/";
	
	@Autowired
	private CustomSqlCache customSqlCache;
	
	private ModelAndView modelAndView = new ModelAndView();
	
	@GetMapping("/" + KEY_CUSTOMER_ORDER_FEE_RANGE_PER_COUNT + "/{path}")
	public ModelAndView getFeeRangePerCount(@PathVariable String path) {				
		LabelValue[] quantity = customSqlCache.getCacheObjectArray().get(KEY_CUSTOMER_ORDER_FEE_RANGE_PER_COUNT);
		modelAndView.clear();
		modelAndView.addObject(ACTIVE_NAV_ITEM, "nav-item-reports");
		modelAndView.addObject(KEY_CUSTOMER_ORDER_FEE_RANGE_PER_COUNT, quantity);
		modelAndView.setViewName(CHART_VIEW + path + "/" + KEY_CUSTOMER_ORDER_FEE_RANGE_PER_COUNT);
		return modelAndView;
	}

	@GetMapping("/" + KEY_CUSTOMER_ORDER_SHIPPING_FEE_PER_MONTH + "/{path}")
	public ModelAndView getShippingFeePerMonth(@PathVariable String path) {
		LabelValue[] quantity = customSqlCache.getCacheObjectArray().get(KEY_CUSTOMER_ORDER_SHIPPING_FEE_PER_MONTH);
		modelAndView.clear();
		modelAndView.addObject(KEY_CUSTOMER_ORDER_SHIPPING_FEE_PER_MONTH, quantity);
		modelAndView.setViewName(CHART_VIEW + path + "/" + KEY_CUSTOMER_ORDER_SHIPPING_FEE_PER_MONTH);
		return modelAndView;
	}
	
	@GetMapping("/" + KEY_PRODUCT_CATEGORY_RATIOS + "/{path}")
	public ModelAndView getCategoryRatios(@PathVariable String path) {
		LabelValue[] ratios = customSqlCache.getCacheObjectArray().get(KEY_PRODUCT_CATEGORY_RATIOS);
		modelAndView.clear();
		modelAndView.addObject(ACTIVE_NAV_ITEM, "nav-item-reports");
		modelAndView.addObject(KEY_PRODUCT_CATEGORY_RATIOS, ratios);
		modelAndView.setViewName(CHART_VIEW + path + "/" + KEY_PRODUCT_CATEGORY_RATIOS);
		return modelAndView;
	}

	@GetMapping("/" + KEY_PRODUCT_PRICE_RANGE_PER_LIST_PRICE + "/{path}")
	public ModelAndView getPriceRangePerListPrice(@PathVariable String path) {
		LabelValue[] priceRange = customSqlCache.getCacheObjectArray().get(KEY_PRODUCT_PRICE_RANGE_PER_LIST_PRICE);
		modelAndView.clear();
		modelAndView.addObject(ACTIVE_NAV_ITEM, "nav-item-reports");
		modelAndView.addObject(KEY_PRODUCT_PRICE_RANGE_PER_LIST_PRICE, priceRange);
		modelAndView.setViewName(CHART_VIEW + path + "/" + KEY_PRODUCT_CATEGORY_RATIOS);
		return modelAndView;
	}

	@GetMapping("/" + KEY_PRODUCT_PRICE_RANGE_PER_STANDARD_COST + "/{path}")
	public ModelAndView getPriceRangePerStandardCost(@PathVariable String path) {
		LabelValue[] priceRange = customSqlCache.getCacheObjectArray().get(KEY_PRODUCT_PRICE_RANGE_PER_STANDARD_COST);
		modelAndView.clear();
		modelAndView.addObject(ACTIVE_NAV_ITEM, "nav-item-reports");
		modelAndView.addObject(KEY_PRODUCT_PRICE_RANGE_PER_STANDARD_COST, priceRange);
		modelAndView.setViewName(CHART_VIEW + path + "/" + KEY_PRODUCT_PRICE_RANGE_PER_STANDARD_COST);
		return modelAndView;
	}

	@GetMapping("/" + KEY_PURCHASE_ORDER_DETAIL_QUANTITY_PER_COST_RANGE + "/{path}")
	public ModelAndView getQuantityPerCostRange(@PathVariable String path) {
		LabelValue[] quantity = customSqlCache.getCacheObjectArray().get(KEY_PURCHASE_ORDER_DETAIL_QUANTITY_PER_COST_RANGE);
		modelAndView.clear();
		modelAndView.addObject(ACTIVE_NAV_ITEM, "nav-item-reports");
		modelAndView.addObject(KEY_PURCHASE_ORDER_DETAIL_QUANTITY_PER_COST_RANGE, quantity);
		modelAndView.setViewName(CHART_VIEW + path + "/" + KEY_PURCHASE_ORDER_DETAIL_QUANTITY_PER_COST_RANGE);
		return modelAndView;
	}

	@GetMapping("/" + KEY_PURCHASE_ORDER_DETAIL_QUANTITY_PER_UNIT_COST + "/{path}")
	public ModelAndView getQuantityPerUnitCost(@PathVariable String path) {
		LabelValue[] quantity = customSqlCache.getCacheObjectArray().get(KEY_PURCHASE_ORDER_DETAIL_QUANTITY_PER_UNIT_COST);
		modelAndView.clear();
		modelAndView.addObject(ACTIVE_NAV_ITEM, "nav-item-reports");
		modelAndView.addObject(KEY_PURCHASE_ORDER_DETAIL_QUANTITY_PER_UNIT_COST, quantity);
		modelAndView.setViewName(CHART_VIEW + path + "/" + KEY_PURCHASE_ORDER_DETAIL_QUANTITY_PER_UNIT_COST);
		return modelAndView;
	}		
	
}
