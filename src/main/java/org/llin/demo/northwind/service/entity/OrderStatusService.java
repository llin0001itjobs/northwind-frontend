package org.llin.demo.northwind.service.entity;

import java.util.ArrayList;
import java.util.List;

import org.llin.demo.northwind.dto.OrderStatusDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class OrderStatusService {

	private final RestClient restClient;

	@Autowired
	public OrderStatusService(@Qualifier("northwindDataClient") RestClient restClient) {
		this.restClient = restClient;
	}

	// ==================================================================
	// Helper classes for HAL _embedded wrapper (placed at class level)
	// ==================================================================

	private static class EmbeddedOrderStatuses {
		@com.fasterxml.jackson.annotation.JsonProperty("_embedded")
		public OrderStatusList OrderStatuses;
	}

	private static class OrderStatusList {
		@com.fasterxml.jackson.annotation.JsonProperty("orderStatuses")
		public List<OrderStatusDto> OrderStatus;
	}

	// ==================================================================
	// Public methods
	// ==================================================================

	/**
	 * GET orderStatus (returns all OrderStatuses)
	 */
	public List<OrderStatusDto> findAll() {
		EmbeddedOrderStatuses response = restClient.get().uri("orderStatus").retrieve()
				.body(EmbeddedOrderStatuses.class);

		return response != null && response.OrderStatuses != null && response.OrderStatuses.OrderStatus != null
				? response.OrderStatuses.OrderStatus
				: List.of();
	}

	public List<OrderStatusDto> findById(Integer id) {
		if (id == null)
			return List.of();

		List<OrderStatusDto> list = new ArrayList<>();

		OrderStatusDto dto = restClient.get().uri("orderStatus/{id}", id).retrieve().body(OrderStatusDto.class);

		if (dto != null)
			list.add(dto);

		return list;
	}

}