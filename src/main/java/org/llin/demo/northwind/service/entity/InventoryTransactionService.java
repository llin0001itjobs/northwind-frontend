package org.llin.demo.northwind.service.entity;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.llin.demo.northwind.dto.CustomerOrderDto;
import org.llin.demo.northwind.dto.InventoryTransactionDto;
import org.llin.demo.northwind.dto.InventoryTransactionTypeDto;
import org.llin.demo.northwind.dto.ProductDto;
import org.llin.demo.northwind.dto.PurchaseOrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

@Service
public class InventoryTransactionService extends _BaseService {

	private final RestClient restClient;

	private final CustomerOrderService customerOrderService;
	private final InventoryTransactionTypeService inventoryTransactionTypeService;
	private final ProductService productService;
	private final PurchaseOrderService purchaseOrderService;

	@Autowired
	public InventoryTransactionService(@Qualifier("northwindDataClient") RestClient restClient,
			CustomerOrderService customerOrderService, InventoryTransactionTypeService inventoryTransactionTypeService,
			ProductService productService, PurchaseOrderService purchaseOrderService) {
		this.restClient = restClient;
		this.customerOrderService = customerOrderService;
		this.inventoryTransactionTypeService = inventoryTransactionTypeService;
		this.productService = productService;
		this.purchaseOrderService = purchaseOrderService;
	}

	// ==================================================================
	// Helper classes for HAL _embedded wrapper (placed at class level)
	// ==================================================================

	private static class EmbeddedInventoryTransactions {
		@com.fasterxml.jackson.annotation.JsonProperty("_embedded")
		public InventoryTransactionList InventoryTransactions;
	}

	private static class InventoryTransactionList {
		@com.fasterxml.jackson.annotation.JsonProperty("inventoryTransactions")
		public List<InventoryTransactionDto> InventoryTransaction;
	}

	// ==================================================================
	// Public methods
	// ==================================================================

	/**
	 * GET /inventoryTransaction (returns all InventoryTransactions)
	 */
	public List<InventoryTransactionDto> findAll() {
		EmbeddedInventoryTransactions response = restClient.get().uri("inventoryTransaction").retrieve()
				.body(EmbeddedInventoryTransactions.class);

		return response != null && response.InventoryTransactions != null
				&& response.InventoryTransactions.InventoryTransaction != null
						? response.InventoryTransactions.InventoryTransaction
						: List.of();
	}

	public Optional<InventoryTransactionDto> findById(Integer id) {
		if (id == null)
			return Optional.empty();

		return Optional.ofNullable(
				restClient.get().uri("inventoryTransaction/{id}", id).retrieve().body(InventoryTransactionDto.class));
	}

	public InventoryTransactionDto create(InventoryTransactionDto inventoryTransactionDto) {
		return restClient.post().uri("inventoryTransaction").body(inventoryTransactionDto).retrieve()
				.body(InventoryTransactionDto.class);
	}

	public InventoryTransactionDto update(Integer id, InventoryTransactionDto inventoryTransactionDto) {
		return restClient.put().uri("inventoryTransaction/{id}", id).body(inventoryTransactionDto).retrieve()
				.body(InventoryTransactionDto.class);
	}

	public void deleteById(Integer id) {
		restClient.delete().uri("inventoryTransaction/{id}", id).retrieve().toBodilessEntity();
	}

	public List<InventoryTransactionDto> findByInventoryTransactionTypeId(Integer id) {
		return findByObject(id, "id", "findByInventoryTransactionTypeId");
	}

	public List<InventoryTransactionDto> findByProductId(Integer id) {
		return findByObject(id, "id", "findByProductId");
	}

	public List<InventoryTransactionDto> findByPurchaseOrderId(Integer id) {
		return findByObject(id, "id", "findByPurchaseOrderId");
	}

	public List<InventoryTransactionDto> findByCustomerOrderId(Integer id) {
		return findByObject(id, "id", "findByCustomerOrderId");
	}

	public List<InventoryTransactionDto> findByCommentsContaining(String comments) {
		return findByObject(comments, "comments", "findByCommentsContaining");
	}

	// Date ranges
	public List<InventoryTransactionDto> getInventoryTransactionsByTransactionCreatedDateBetween(LocalDateTime start,
			LocalDateTime end) {
		return findWithTwoParameters(start, "start", end, "end",
				"getInventoryTransactionsByTransactionCreatedDateBetween");
	}

	public List<InventoryTransactionDto> getInventoryTransactionsByTransactionModifiedDateBetween(LocalDateTime start,
			LocalDateTime end) {
		return findWithTwoParameters(start, "start", end, "end",
				"getInventoryTransactionsByTransactionModifiedDateBetween");
	}

	// Quantity ranges
	public List<InventoryTransactionDto> getInventoryTransactionsByQuantityBetween(Integer minQty, Integer maxQty) {
		return findWithTwoParameters(minQty, "minQty", maxQty, "maxQty", "getInventoryTransactionsByQuantityBetween");
	}

	private List<InventoryTransactionDto> findByObject(Object value, String paramName, String searchMethod) {
		if (value == null) {
			return Collections.emptyList();
		}

		try {
			return restClient.get()
					.uri(uriBuilder -> uriBuilder.path("inventoryTransaction/search/{method}")
							.queryParam(paramName, value).build(searchMethod))
					.retrieve().body(new ParameterizedTypeReference<List<InventoryTransactionDto>>() {
					});
		} catch (HttpClientErrorException.NotFound e) {
			return Collections.emptyList();
		}
	}

	private List<InventoryTransactionDto> findWithTwoParameters(Object param1, String paramName1, Object param2,
			String paramName2, String path) {
		try {
			return restClient.get()
					.uri(uriBuilder -> uriBuilder.path("inventoryTransaction/search/" + path)
							.queryParam(paramName1, param1).queryParam(paramName2, param2).build())
					.retrieve().body(new ParameterizedTypeReference<List<InventoryTransactionDto>>() {
					});
		} catch (HttpClientErrorException.NotFound e) {
			return Collections.emptyList();
		}
	}
	
	public InventoryTransactionDto enrich(InventoryTransactionDto dto) {
	    if (dto == null) return null;

	    Integer id = extractIdFromSelfLink(dto);  // prefer link-based ID
	    
	    Optional<CustomerOrderDto> coOpt = customerOrderService.findById(extractCustomerOrderId(dto));
	    Optional<InventoryTransactionTypeDto> ittOpt = inventoryTransactionTypeService.findById(extractInventoryTransactionTypeId(dto));
	    Optional<ProductDto> prOpt = productService.findById(extractProductId(dto));
	    Optional<PurchaseOrderDto> poOpt = purchaseOrderService.findById(extractPurchaseOrderId(dto));
	    
	    CustomerOrderDto customerOrder = coOpt.orElse(null);  
	    InventoryTransactionTypeDto inventoryTransactionType = ittOpt.orElse(null);
	    ProductDto product = prOpt.orElse(null);
	    PurchaseOrderDto purchaseOrder = poOpt.orElse(null);
			    
	    return new InventoryTransactionDto(
	        id != null ? id : dto.id(),   // fallback if extraction fails
	        		product,
	        		inventoryTransactionType,
	        		dto.transactionCreatedDate(),
	        		dto.transactionModifiedDate(),
	        		dto.quantity(),
	        		dto.comments(),
	        		customerOrder,
	        		purchaseOrder,
	        dto.links()
	    );
	}
	
	private Integer extractCustomerOrderId(InventoryTransactionDto i) {
		if (i == null || i.links() == null)
			return null;
		return extractIdFromLink(i.links().getLink("customerOrder"));
	}

	private Integer extractInventoryTransactionTypeId(InventoryTransactionDto i) {
		if (i == null || i.links() == null)
			return null;
		return extractIdFromLink(i.links().getLink("transactionType"));
	}

	private Integer extractProductId(InventoryTransactionDto i) {
		if (i == null || i.links() == null)
			return null;
		return extractIdFromLink(i.links().getLink("product"));
	}

	private Integer extractPurchaseOrderId(InventoryTransactionDto i) {
		if (i == null || i.links() == null)
			return null;
		return extractIdFromLink(i.links().getLink("purchaseOrder"));
	}
	
	private Integer extractIdFromSelfLink(InventoryTransactionDto p) {
		if (p == null || p.links() == null)
			return null;
		return extractIdFromSelf(p.links().getLink("self"));
	}
}