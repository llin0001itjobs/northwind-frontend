package org.llin.demo.northwind.service.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.llin.demo.northwind.dto.InventoryTransactionDto;
import org.llin.demo.northwind.dto.LabelDoubleValueLongDto;
import org.llin.demo.northwind.dto.LabelValueLongDto;
import org.llin.demo.northwind.dto.ProductDto;
import org.llin.demo.northwind.dto.PurchaseOrderDetailDto;
import org.llin.demo.northwind.dto.PurchaseOrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

@Service
public class PurchaseOrderDetailService extends _BaseService {
	private final RestClient restClient;

	private final InventoryTransactionService inventoryTransactionService;
	private final ProductService productService;
	private final PurchaseOrderService purchaseOrderService;
	
	@Autowired
	public PurchaseOrderDetailService(@Qualifier("northwindDataClient") RestClient restClient, InventoryTransactionService inventoryTransactionService,
			ProductService productService, PurchaseOrderService purchaseOrderService) {	
		this.restClient = restClient;
		this.inventoryTransactionService = inventoryTransactionService;
		this.productService = productService;
		this.purchaseOrderService = purchaseOrderService;
	}

	// ==================================================================
	// Helper classes for HAL _embedded wrapper (placed at class level)
	// ==================================================================

	private static class EmbeddedPurchaseOrderDetails {
		@com.fasterxml.jackson.annotation.JsonProperty("_embedded")
		public PurchaseOrderDetailList PurchaseOrderDetails;
	}

	private static class PurchaseOrderDetailList {
		@com.fasterxml.jackson.annotation.JsonProperty("purchaseOrderDetails")
		public List<PurchaseOrderDetailDto> PurchaseOrderDetail;
	}
	
		private static class EmbeddedLabelValueLongs {
		@com.fasterxml.jackson.annotation.JsonProperty("_embedded")
		public LabelValueLongList LabelValueLongs;
	}

	private static class LabelValueLongList {
		@com.fasterxml.jackson.annotation.JsonProperty("labelValueLong")
		public List<LabelValueLongDto> LabelValueLong;
	}
	
	private static class EmbeddedLabelDoubleValueLongs {
		@com.fasterxml.jackson.annotation.JsonProperty("_embedded")
		public LabelDoubleValueLongList LabelDoubleValueLongs;
	}

	private static class LabelDoubleValueLongList {
		@com.fasterxml.jackson.annotation.JsonProperty("labelDoubleValueLong")
		public List<LabelDoubleValueLongDto> LabelDoubleValueLong;
	}
	
	

	// ==================================================================
	// Public methods
	// ==================================================================

	public Optional<PurchaseOrderDetailDto> findById(Integer id) {
		if (id == null)
			return Optional.empty();

		return Optional.ofNullable(
				restClient.get().uri("purchaseOrderDetail/{id}", id).retrieve().body(PurchaseOrderDetailDto.class));
	}

	/**
	 * GET purchaseOrderDetail (returns all PurchaseOrderDetails)
	 */
	public List<PurchaseOrderDetailDto> findAll() {
		EmbeddedPurchaseOrderDetails response = restClient.get().uri("purchaseOrderDetail").retrieve()
				.body(EmbeddedPurchaseOrderDetails.class);

		return response != null && response.PurchaseOrderDetails != null
				&& response.PurchaseOrderDetails.PurchaseOrderDetail != null
						? response.PurchaseOrderDetails.PurchaseOrderDetail
						: List.of();
	}

	public PurchaseOrderDetailDto create(PurchaseOrderDetailDto PurchaseOrderDetailDto) {
		return restClient.post().uri("purchaseOrderDetail").body(PurchaseOrderDetailDto).retrieve()
				.body(PurchaseOrderDetailDto.class);
	}

	public PurchaseOrderDetailDto update(Integer id, PurchaseOrderDetailDto PurchaseOrderDetailDto) {
		return restClient.put().uri("purchaseOrderDetail/{id}", id).body(PurchaseOrderDetailDto).retrieve()
				.body(PurchaseOrderDetailDto.class);
	}

	public void deleteById(Integer id) {
		restClient.delete().uri("purchaseOrderDetail/{id}", id).retrieve().toBodilessEntity();
	}
	
	public List<LabelValueLongDto> shippingFeePerMonth() {
		EmbeddedLabelValueLongs response = restClient.get()
    			.uri("purchaseOrderDetail/shipping-fee-per-month").retrieve()
    			.body(EmbeddedLabelValueLongs.class);
    	return response != null && response.LabelValueLongs != null && response.LabelValueLongs.LabelValueLong != null
				? response.LabelValueLongs.LabelValueLong
				: List.of();		
    }
	
	public List<LabelDoubleValueLongDto> quantityPerUnitCost() {
		EmbeddedLabelDoubleValueLongs response = restClient.get()
    			.uri("purchaseOrderDetail/quantity-per-unit-cost").retrieve()
    			.body(EmbeddedLabelDoubleValueLongs.class);
    	return response != null && response.LabelDoubleValueLongs != null && response.LabelDoubleValueLongs.LabelDoubleValueLong != null
				? response.LabelDoubleValueLongs.LabelDoubleValueLong
				: List.of();		
    }
	
    public List<PurchaseOrderDetailDto> findByPurchaseOrderId(Integer id) {
    	return findByObject(id, "id", "findByPurchaseOrderId");
    }
    
    public List<PurchaseOrderDetailDto> findByProductId(Integer id) {
    	return findByObject(id, "id", "findByProductId");
    }
    
    public List<PurchaseOrderDetailDto> findByInventoryTransactionId(Integer id) {
    	return findByObject(id, "id", "findByInventoryTransactionId");
    }

    public List<PurchaseOrderDetailDto> findByQuantityBetweenOrderByQuantityAsc(BigDecimal min, BigDecimal max) {
    	return findWithTwoParameters(min, "min", max, "max", "findByQuantityBetweenOrderByQuantityAsc");
    }
    
    public List<PurchaseOrderDetailDto> findByUnitCostBetweenOrderByUnitCostAsc(BigDecimal min, BigDecimal max) {
    	return findWithTwoParameters(min, "min", max, "max", "findByUnitCostBetweenOrderByUnitCostAsc");
    }

    public List<PurchaseOrderDetailDto> findByDateReceivedBetweenOrderByDateReceivedAsc(LocalDateTime start, LocalDateTime end) {
    	return findWithTwoParameters(start, "start", end, "end", "findByDateReceivedBetweenOrderByDateReceivedAsc");
    }
    
    public List<PurchaseOrderDetailDto> findByPostedToInventory(Boolean postedToInventory) {
    	return findByObject(postedToInventory, "postedToInventory", "findByPostedToInventory");
    }
    
    private List<PurchaseOrderDetailDto> findByObject(Object value, String paramName, String searchMethod) {
        if (value == null) {
            return Collections.emptyList();
        }

        try {
            return restClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path("purchaseOrderDetail/search/{method}")
                            .queryParam(paramName, value)
                            .build(searchMethod))
                    .retrieve()
                    .body(new ParameterizedTypeReference<List<PurchaseOrderDetailDto>>() {});
        } catch (HttpClientErrorException.NotFound e) {
            return Collections.emptyList();
        }
    }
    
    private List<PurchaseOrderDetailDto> findWithTwoParameters(Object param1, String paramName1, 
			 											 	   Object param2, String paramName2, String path) {
        try {
			return restClient.get()
			.uri(uriBuilder -> uriBuilder.path("purchaseOrderDetail/search/" + path)
			.queryParam(paramName1, param1)
			.queryParam(paramName2, param2)
			.build())
			.retrieve()
			.body(new ParameterizedTypeReference<List<PurchaseOrderDetailDto>>() {});
        } catch (HttpClientErrorException.NotFound e) {
            return Collections.emptyList();
        }			
    }
    
	public PurchaseOrderDetailDto enrich(PurchaseOrderDetailDto dto) {
		if (dto == null)
			return null;

		Integer id = extractIdFromSelfLink(dto); // prefer link-based ID

		Optional<InventoryTransactionDto> itOpt = inventoryTransactionService
				.findById(extractInventoryTransactionId(dto));
		Optional<ProductDto> prOpt = productService.findById(extractProductId(dto));
		Optional<PurchaseOrderDto> poOpt = purchaseOrderService.findById(extractPurchaseOrderId(dto));

		InventoryTransactionDto inventoryTransaction = inventoryTransactionService.enrich(itOpt.orElse(null));
		ProductDto product = productService.enrich(prOpt.orElse(null));
		PurchaseOrderDto purchaseOrder = purchaseOrderService.enrich(poOpt.orElse(null));
		
		return new PurchaseOrderDetailDto(id != null ? id : dto.id(), 
				inventoryTransaction,  product, purchaseOrder, dto.quantity(),
				dto.unitCost(), dto.dateReceived(), dto.postedToInventory(), dto.links());
	}
	
	private Integer extractInventoryTransactionId(PurchaseOrderDetailDto p) {
		if (p == null || p.links() == null)
			return null;
		Integer ii = extractIdFromLink(p.links().getLink("inventoryTransaction"));
		p.links().getLink("inventoryTransaction").setId(ii == null ? null : ii.toString());
		return ii;		
	}
	
	private Integer extractProductId(PurchaseOrderDetailDto p) {
		if (p == null || p.links() == null)
			return null;
		Integer ii = extractIdFromLink(p.links().getLink("product"));
		p.links().getLink("product").setId(ii == null ? null : ii.toString());
		return ii;			
	}

	private Integer extractPurchaseOrderId(PurchaseOrderDetailDto p) {
		if (p == null || p.links() == null)
			return null;
		Integer ii = extractIdFromLink(p.links().getLink("purchaseOrder"));
		p.links().getLink("purchaseOrder").setId(ii == null ? null : ii.toString());
		return ii;				
	}

	private Integer extractIdFromSelfLink(PurchaseOrderDetailDto p) {
		if (p == null || p.links() == null)
			return null;
		return extractIdFromSelf(p.links().getLink("self"));
	}
}
