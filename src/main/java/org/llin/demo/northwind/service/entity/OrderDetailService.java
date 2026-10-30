package org.llin.demo.northwind.service.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.llin.demo.northwind.dto.CustomerOrderDto;
import org.llin.demo.northwind.dto.InventoryTransactionDto;
import org.llin.demo.northwind.dto.OrderDetailDto;
import org.llin.demo.northwind.dto.OrderStatusDto;
import org.llin.demo.northwind.dto.ProductDto;
import org.llin.demo.northwind.dto.PurchaseOrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

@Service
public class OrderDetailService extends _BaseService {

	private final RestClient restClient;

	private final CustomerOrderService customerOrderService;
	private final InventoryTransactionService inventoryTransactionService;
	private final OrderStatusService orderStatusService;
	private final ProductService productService;
	private final PurchaseOrderService purchaseOrderService;

	@Autowired
	public OrderDetailService(@Qualifier("northwindDataClient") RestClient restClient,
			CustomerOrderService customerOrderService, InventoryTransactionService inventoryTransactionService,
			OrderStatusService orderStatusService, ProductService productService,
			PurchaseOrderService purchaseOrderService) {
		this.restClient = restClient;
		this.customerOrderService = customerOrderService;
		this.inventoryTransactionService = inventoryTransactionService;
		this.orderStatusService = orderStatusService;
		this.productService = productService;
		this.purchaseOrderService = purchaseOrderService;
	}

	// ==================================================================
	// Helper classes for HAL _embedded wrapper (placed at class level)
	// ==================================================================

	private static class EmbeddedOrderDetails {
		@com.fasterxml.jackson.annotation.JsonProperty("_embedded")
		public OrderDetailList orderDetails;
	}

	private static class OrderDetailList {
		@com.fasterxml.jackson.annotation.JsonProperty("orderDetails")
		public List<OrderDetailDto> orderDetails;
	}

	// ==================================================================
	// Public methods
	// ==================================================================

	public Optional<OrderDetailDto> findById(Integer id) {
		if (id == null)
			return Optional.empty();

		return Optional.ofNullable(restClient.get().uri("orderDetail/{id}", id).retrieve().body(OrderDetailDto.class));
	}

	/**
	 * GET /orderDetail (returns all OrderDetails)
	 */
	public List<OrderDetailDto> findAll() {
		EmbeddedOrderDetails response = restClient.get().uri("orderDetail").retrieve().body(EmbeddedOrderDetails.class);

		return response != null && response.orderDetails != null && response.orderDetails.orderDetails != null
				? response.orderDetails.orderDetails
				: List.of();
	}

	public OrderDetailDto create(OrderDetailDto OrderDetailDto) {
		return restClient.post().uri("orderDetail").body(OrderDetailDto).retrieve().body(OrderDetailDto.class);
	}

	public OrderDetailDto update(Integer id, OrderDetailDto OrderDetailDto) {
		return restClient.put().uri("orderDetail/{id}", id).body(OrderDetailDto).retrieve().body(OrderDetailDto.class);
	}

	public void deleteById(Integer id) {
		restClient.delete().uri("orderDetail/{id}", id).retrieve().toBodilessEntity();
	}

	public List<OrderDetailDto> findByCustomerOrderId(Integer id) {
		return findByObject(id, "id", "findByCustomerOrderId");
	}

	public List<OrderDetailDto> findByProductId(Integer id) {
		return findByObject(id, "id", "findByProductId");
	}

	public List<OrderDetailDto> findByOrderStatusId(Integer id) {
		return findByObject(id, "id", "findByOrderStatusId");
	}

	public List<OrderDetailDto> findByPurchaseOrderId(Integer id) {
		return findByObject(id, "id", "findByPurchaseOrderId");
	}

	public List<OrderDetailDto> findByInventoryTransactionId(Integer id) {
		return findByObject(id, "id", "findByInventoryTransactionId");
	}

	// Numeric ranges
	public List<OrderDetailDto> findByQuantityBetweenOrderByQuantityAsc(BigDecimal min, BigDecimal max) {
		return findWithTwoParameters(min, "min", max, "max", "findByQuantityBetweenOrderByQuantityAsc");
	}

	public List<OrderDetailDto> findByUnitPriceBetweenOrderByUnitPriceAsc(BigDecimal min, BigDecimal max) {
		return findWithTwoParameters(min, "min", max, "max", "findByUnitPriceBetweenOrderByUnitPriceAsc");
	}

	public List<OrderDetailDto> findByDiscountBetweenOrderByDiscountAsc(Double min, Double max) {
		return findWithTwoParameters(min, "min", max, "max", "findByDiscountBetweenOrderByDiscountAsc");
	}

	public List<OrderDetailDto> findByDateAllocatedBetweenOrderByDateAllocatedAsc(LocalDateTime start,
			LocalDateTime end) {
		return findWithTwoParameters(start, "start", end, "end", "findByDateAllocatedBetweenOrderByDateAllocatedAsc");
	}

	private List<OrderDetailDto> findByObject(Object value, String paramName, String searchMethod) {
		if (value == null) {
			return Collections.emptyList();
		}

		try {
			return restClient.get()
					.uri(uriBuilder -> uriBuilder.path("orderDetail/search/{method}").queryParam(paramName, value)
							.build(searchMethod))
					.retrieve().body(new ParameterizedTypeReference<List<OrderDetailDto>>() {
					});
		} catch (HttpClientErrorException.NotFound e) {
			return Collections.emptyList();
		}
	}

	private List<OrderDetailDto> findWithTwoParameters(Object param1, String paramName1, Object param2,
			String paramName2, String path) {
		try {
			return restClient.get()
					.uri(uriBuilder -> uriBuilder.path("orderDetail/search/" + path).queryParam(paramName1, param1)
							.queryParam(paramName2, param2).build())
					.retrieve().body(new ParameterizedTypeReference<List<OrderDetailDto>>() {
					});
		} catch (HttpClientErrorException.NotFound e) {
			return Collections.emptyList();
		}
	}

	public OrderDetailDto enrich(OrderDetailDto dto) {
		if (dto == null)
			return null;

		Integer id = extractIdFromSelfLink(dto); // prefer link-based ID
		Integer coId = extractCustomerOrderId(dto);
		Optional<CustomerOrderDto> coOpt = customerOrderService.findById(coId);
		
		Optional<InventoryTransactionDto> itOpt = inventoryTransactionService.findById(extractInventoryTransactionId(dto));
		Optional<OrderStatusDto> osOpt = orderStatusService.findById(extractOrderStatusId(dto));
		Optional<ProductDto> prOpt = productService.findById(extractProductId(dto));
		Optional<PurchaseOrderDto> poOpt = purchaseOrderService.findById(extractPurchaseOrderId(dto));

		CustomerOrderDto customerOrder = customerOrderService.enrich(coOpt.orElse(null));						
		InventoryTransactionDto inventoryTransaction = inventoryTransactionService.enrich(itOpt.orElse(null));
		OrderStatusDto orderStatus = osOpt.orElse(null);
		ProductDto product = productService.enrich(prOpt.orElse(null));
		PurchaseOrderDto purchaseOrder = purchaseOrderService.enrich(poOpt.orElse(null));
								
		inventoryTransaction = new InventoryTransactionDto(inventoryTransaction.id(), inventoryTransaction.product(), inventoryTransaction.inventoryTransactionType(), inventoryTransaction.transactionCreatedDate(), 
				inventoryTransaction.transactionModifiedDate(), inventoryTransaction.quantity(), inventoryTransaction.comments(), inventoryTransaction.customerOrder(), inventoryTransaction.purchaseOrder(), inventoryTransaction.links());
				
		return new OrderDetailDto(id != null ? id : dto.id(), // fallback if extraction fails
				customerOrder, inventoryTransaction, orderStatus, product, purchaseOrder, dto.quantity(),
				dto.unitPrice(), dto.discount(), dto.dateAllocated(), dto.links());
	}

	private Integer extractCustomerOrderId(OrderDetailDto o) {
		if (o == null || o.links() == null)
			return null;
		Integer ii = extractIdFromLink(o.links().getLink("customerOrder"));
		o.links().getLink("customerOrder").setId(ii == null ? null : ii.toString());
		return ii;
	}

	private Integer extractInventoryTransactionId(OrderDetailDto o) {
		if (o == null || o.links() == null)
			return null;
		Integer ii = extractIdFromLink(o.links().getLink("inventoryTransaction"));
		o.links().getLink("inventoryTransaction").setId(ii == null ? null : ii.toString());
		return ii;		
	}

	private Integer extractOrderStatusId(OrderDetailDto o) {
		if (o == null || o.links() == null)
			return null;
		return extractIdFromLink(o.links().getLink("orderStatus"));
	}

	private Integer extractProductId(OrderDetailDto o) {
		if (o == null || o.links() == null)
			return null;
		Integer ii = extractIdFromLink(o.links().getLink("product"));
		o.links().getLink("product").setId(ii == null ? null : ii.toString());
		return ii;				
	}

	private Integer extractPurchaseOrderId(OrderDetailDto o) {
		if (o == null || o.links() == null)
			return null;
		Integer ii = extractIdFromLink(o.links().getLink("purchaseOrder"));
		o.links().getLink("purchaseOrder").setId(ii == null ? null : ii.toString());
		return ii;				
	}

	private Integer extractIdFromSelfLink(OrderDetailDto o) {
		if (o == null || o.links() == null)
			return null;
		return extractIdFromSelf(o.links().getLink("self"));
	}

}