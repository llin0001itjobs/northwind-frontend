package org.llin.demo.northwind.service.entity;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.llin.demo.northwind.dto.InventoryTransactionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

@Service
public class InventoryTransactionService {

	private final RestClient restClient;

	@Autowired
	public InventoryTransactionService(RestClient restClient) {
		this.restClient = restClient;
	}

	// ==================================================================
	// Helper classes for HAL _embedded wrapper (placed at class level)
	// ==================================================================

	private static class EmbeddedInventoryTransactions {
		@com.fasterxml.jackson.annotation.JsonProperty("_embedded")
		public InventoryTransactionList InventoryTransactions;
	}

	private static class InventoryTransactionList {
		@com.fasterxml.jackson.annotation.JsonProperty("inventoryTransaction")
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
					.uri(uriBuilder -> uriBuilder
					.path("inventoryTransaction/search/{method}")
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
					.uri(uriBuilder -> uriBuilder
					.path("inventoryTransaction/search/" + path)
					.queryParam(paramName1, param1)
					.queryParam(paramName2, param2).build())
					.retrieve().body(new ParameterizedTypeReference<List<InventoryTransactionDto>>() {
					});
		} catch (HttpClientErrorException.NotFound e) {
			return Collections.emptyList();
		}
	}

}