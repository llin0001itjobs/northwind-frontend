package org.llin.demo.northwind.service.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.llin.demo.northwind.dto.CompanyDto;
import org.llin.demo.northwind.dto.CustomerDto;
import org.llin.demo.northwind.dto.CustomerOrderDto;
import org.llin.demo.northwind.dto.EmployeeDto;
import org.llin.demo.northwind.dto.LabelIntValueDoubleDto;
import org.llin.demo.northwind.dto.LabelIntValueLongDto;
import org.llin.demo.northwind.dto.LabelValueLongDto;
import org.llin.demo.northwind.dto.OrderStatusDto;
import org.llin.demo.northwind.dto.OrderTaxStatusDto;
import org.llin.demo.northwind.dto.PaymentTypeDto;
import org.llin.demo.northwind.dto.ShipperDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

@Service
public class CustomerOrderService extends _BaseService {

	private final RestClient restClient;

	@Autowired
	private CompanyService companyService;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private ShipperService shipperService;

	@Autowired
	private OrderStatusService orderStatusService;

	@Autowired
	private OrderTaxStatusService orderTaxStatusService;

	@Autowired
	private PaymentTypeService paymentTypeService;

	@Autowired
	public CustomerOrderService(@Qualifier("northwindDataClient") RestClient restClient) {
		this.restClient = restClient;
	}

	// ==================================================================
	// Helper classes for HAL _embedded wrapper (placed at class level)
	// ==================================================================

	private static class EmbeddedCustomerOrders {
		@com.fasterxml.jackson.annotation.JsonProperty("_embedded")
		public CustomerOrderList CustomerOrders;
	}

	private static class CustomerOrderList {
		@com.fasterxml.jackson.annotation.JsonProperty("customerOrders")
		public List<CustomerOrderDto> CustomerOrder;
	}

	private static class EmbeddedLabelValueLongs {
		@com.fasterxml.jackson.annotation.JsonProperty("_embedded")
		public LabelValueLongList LabelValueLongs;
	}

	private static class LabelValueLongList {
		@com.fasterxml.jackson.annotation.JsonProperty("labelValueLong")
		public List<LabelValueLongDto> LabelValueLong;
	}

	private static class EmbeddedLabelIntValueDoubles {
		@com.fasterxml.jackson.annotation.JsonProperty("_embedded")
		public LabelIntValueDoubleList LabelIntValueDoubles;
	}

	private static class LabelIntValueDoubleList {
		@com.fasterxml.jackson.annotation.JsonProperty("labelIntValueDouble")
		public List<LabelIntValueDoubleDto> LabelIntValueDouble;
	}

	private static class EmbeddedLabelIntValueLongs {
		@com.fasterxml.jackson.annotation.JsonProperty("_embedded")
		public LabelIntValueLongList LabelIntValueLongs;
	}

	private static class LabelIntValueLongList {
		@com.fasterxml.jackson.annotation.JsonProperty("labelIntValueLong")
		public List<LabelIntValueLongDto> LabelIntValueLong;
	}

	// ==================================================================
	// Public methods
	// ==================================================================

	public List<CustomerOrderDto> findAll() {
		EmbeddedCustomerOrders response = restClient.get().uri("customerOrder").retrieve()
				.body(EmbeddedCustomerOrders.class);

		return response != null && response.CustomerOrders != null && response.CustomerOrders.CustomerOrder != null
				? response.CustomerOrders.CustomerOrder
				: List.of();
	}

	public Optional<CustomerOrderDto> findById(Integer id) {
		if (id == null)
			return Optional.empty();

		return Optional
				.ofNullable(restClient.get().uri("customerOrder/{id}", id).retrieve().body(CustomerOrderDto.class));
	}

	public CustomerOrderDto create(CustomerOrderDto CustomerOrderDto) {
		return restClient.post().uri("customerOrder").body(CustomerOrderDto).retrieve().body(CustomerOrderDto.class);
	}

	public CustomerOrderDto update(Integer id, CustomerOrderDto CustomerOrderDto) {
		return restClient.put().uri("customerOrder/{id}", id).body(CustomerOrderDto).retrieve()
				.body(CustomerOrderDto.class);
	}

	public void deleteById(Integer id) {
		restClient.delete().uri("customerOrder/{id}", id).retrieve().toBodilessEntity();
	}

	public List<LabelValueLongDto> feeRangePerCount() {
		EmbeddedLabelValueLongs response = restClient.get().uri("customerOrder/search/fee-range-count").retrieve()
				.body(EmbeddedLabelValueLongs.class);
		return response != null && response.LabelValueLongs != null && response.LabelValueLongs.LabelValueLong != null
				? response.LabelValueLongs.LabelValueLong
				: List.of();
	}

	public List<LabelIntValueDoubleDto> shippingFeePerMonth() {
		EmbeddedLabelIntValueDoubles response = restClient.get().uri("customerOrder/search/shipping-fee-month")
				.retrieve().body(EmbeddedLabelIntValueDoubles.class);
		return response != null && response.LabelIntValueDoubles != null
				&& response.LabelIntValueDoubles.LabelIntValueDouble != null
						? response.LabelIntValueDoubles.LabelIntValueDouble
						: List.of();
	}

	public List<LabelIntValueLongDto> orderCountPerMonth() {
		EmbeddedLabelIntValueLongs response = restClient.get().uri("customerOrder/search/order-count-month").retrieve()
				.body(EmbeddedLabelIntValueLongs.class);
		return response != null && response.LabelIntValueLongs != null
				&& response.LabelIntValueLongs.LabelIntValueLong != null ? response.LabelIntValueLongs.LabelIntValueLong
						: List.of();
	}

	public List<LabelValueLongDto> ordersByStatus() {
		EmbeddedLabelValueLongs response = restClient.get().uri("customerOrder/search/by-status").retrieve()
				.body(EmbeddedLabelValueLongs.class);
		return response != null && response.LabelValueLongs != null && response.LabelValueLongs.LabelValueLong != null
				? response.LabelValueLongs.LabelValueLong
				: List.of();
	}

	public List<CustomerOrderDto> findByCustomerId(Integer id) {
		return findByObject(id, "id", "findByCustomerId");
	}

	// Optional: sort by most recent orders
	public List<CustomerOrderDto> findByCustomerIdOrderByOrderDateDesc(Integer customerId) {
		return findByObject(customerId, "customerId", "findByCustomerIdOrderByOrderDateDesc");
	}

	public List<CustomerOrderDto> findByCustomerIdAndShipCityContaining(Integer customerId, String shipCity) {
		return findWithTwoParameters(customerId, "customerId", shipCity, "shipCity",
				"findByCustomerIdAndShipCityContaining");
	}

	public List<CustomerOrderDto> findByEmployeeId(Integer id) {
		return findByObject(id, "id", "findByEmployeeId");
	}

	public List<CustomerOrderDto> findByShipperId(Integer id) {
		return findByObject(id, "id", "findByShipperId");
	}

	public List<CustomerOrderDto> findByOrderStatusId(Integer id) {
		return findByObject(id, "id", "findByOrderStatusId");
	}

	public List<CustomerOrderDto> findByOrderTaxStatusId(Integer id) {
		return findByObject(id, "id", "findByOrderTaxStatusId");
	}

	public List<CustomerOrderDto> findByShipName(String shipName) {
		return findByObject(shipName, "shipName", "findByShipName");
	}

	public List<CustomerOrderDto> findByShipNameContaining(String shipName) {
		return findByObject(shipName, "shipName", "findByShipNameContaining");
	}

	public List<CustomerOrderDto> findByShipCity(String shipCity) {
		return findByObject(shipCity, "shipCity", "findByShipCity");
	}

	public List<CustomerOrderDto> findByShipCityContaining(String shipCity) {
		return findByObject(shipCity, "shipCity", "findByShipCityContaining");
	}

	public List<CustomerOrderDto> findByShipStateProvince(String shipStateProvince) {
		return findByObject(shipStateProvince, "shipStateProvince", "findByShipStateProvince");
	}

	public List<CustomerOrderDto> findByShipStateProvinceContaining(String shipStateProvince) {
		return findByObject(shipStateProvince, "shipStateProvince", "findByShipStateProvinceContaining");
	}

	public List<CustomerOrderDto> findByShipCountryRegion(String shipCountryRegion) {
		return findByObject(shipCountryRegion, "shipCountryRegion", "findByShipCountryRegion");
	}

	public List<CustomerOrderDto> findByShipCountryRegionContaining(String shipCountryRegion) {
		return findByObject(shipCountryRegion, "shipCountryRegion", "findByShipCountryRegionContaining");
	}

	public List<CustomerOrderDto> findByNotesContaining(String notes) {
		return findByObject(notes, "notes", "findByNotesContaining");
	}

	public List<CustomerOrderDto> findByPaymentType(String paymentType) {
		return findByObject(paymentType, "paymentType", "findByPaymentType");
	}

	public List<CustomerOrderDto> findByOrderDateBetweenOrderByOrderDateAsc(LocalDateTime startDate,
			LocalDateTime endDate) {
		return findWithTwoParameters(startDate, "startDate", endDate, "endDate",
				"findByOrderDateBetweenOrderByOrderDateAsc");
	}

	public List<CustomerOrderDto> findByShippedDateBetweenOrderByShippedDateAsc(LocalDateTime startDate,
			LocalDateTime endDate) {
		return findWithTwoParameters(startDate, "startDate", endDate, "endDate",
				"findByShippedDateBetweenOrderByShippedDateAsc");
	}

	public List<CustomerOrderDto> findByPaidDateBetweenOrderByPaidDateAsc(LocalDateTime startDate,
			LocalDateTime endDate) {
		return findWithTwoParameters(startDate, "startDate", endDate, "endDate",
				"findByPaidDateBetweenOrderByPaidDateAsc");
	}

	// Single-date helpers
	public List<CustomerOrderDto> findByOrderDateAfterOrderByOrderDateAsc(LocalDateTime date) {
		return findByObject(date, "date", "findByOrderDateAfterOrderByOrderDateAsc");
	}

	public List<CustomerOrderDto> findByShippedDateIsNull() { // not yet shipped
		return findByNull("findByShippedDateIsNull");
	}

	public List<CustomerOrderDto> findByPaidDateIsNull() { // not yet paid
		return findByNull("findByPaidDateIsNull");
	}

	// === Money / numeric ranges ===
	public List<CustomerOrderDto> findByShippingFeeBetweenOrderByShippingFeeAsc(BigDecimal minFee, BigDecimal maxFee) {
		return findWithTwoParameters(minFee, "minFee", maxFee, "maxFee",
				"findByShippingFeeBetweenOrderByShippingFeeAsc");
	}

	public List<CustomerOrderDto> findByTaxesBetweenOrderByTaxesAsc(BigDecimal minTax, BigDecimal maxTax) {
		return findWithTwoParameters(minTax, "minTax", maxTax, "maxTax", "findByTaxesBetweenOrderByTaxesAsc");
	}

	public List<CustomerOrderDto> findByTaxRateBetweenOrderByTaxRateAsc(BigDecimal minRate, BigDecimal maxRate) {
		return findWithTwoParameters(minRate, "minRate", maxRate, "maxRate", "findByTaxRateBetweenOrderByTaxRateAsc");
	}

	public List<CustomerOrderDto> findByCustomerIdAndOrderStatusId(Integer customerId, Integer statusId) {
		return findWithTwoParameters(customerId, "customerId", statusId, "statusId",
				"findByCustomerIdAndOrderStatusId");
	}

	public List<CustomerOrderDto> findByOrderDateBetweenAndOrderStatusIdOrderByOrderDateAsc(LocalDateTime startDate,
			LocalDateTime endDate, Integer statusId) {
		return findWithThreeParameters(startDate, "startDate", endDate, "endDate", statusId, "statusId",
				"findByOrderDateBetweenAndOrderStatusIdOrderByOrderDateAsc");
	}

	public List<CustomerOrderDto> findByOrderStatusIdAndPaidDateIsNull(Integer statusId) { // pending payment
		return findByObjectAndNullField(statusId, "statusId", "findByOrderStatusIdAndPaidDateIsNull");
	}

	private List<CustomerOrderDto> findByNull(String path) {
		if (path == null || path.isEmpty()) {
			return Collections.emptyList();
		}

		try {
			return restClient.get().uri(uriBuilder -> uriBuilder.path("customerOrder/search/" + path).build())
					.retrieve().body(new ParameterizedTypeReference<List<CustomerOrderDto>>() {
					});
		} catch (HttpClientErrorException.NotFound e) {
			return Collections.emptyList();
		}
	}

	private List<CustomerOrderDto> findByObject(Object value, String paramName, String searchMethod) {
		if (value == null) {
			return Collections.emptyList();
		}

		try {
			return restClient.get()
					.uri(uriBuilder -> uriBuilder.path("customerOrder/search/{method}").queryParam(paramName, value)
							.build(searchMethod))
					.retrieve().body(new ParameterizedTypeReference<List<CustomerOrderDto>>() {
					});
		} catch (HttpClientErrorException.NotFound e) {
			return Collections.emptyList();
		}
	}

	private List<CustomerOrderDto> findByObjectAndNullField(Object param, String paramLabel, String path) {
		if (param == null) {
			return Collections.emptyList();
		}

		try {
			return restClient.get().uri(
					uriBuilder -> uriBuilder.path("customerOrder/search/" + path).queryParam(paramLabel, param).build())
					.retrieve().body(new ParameterizedTypeReference<List<CustomerOrderDto>>() {
					});
		} catch (HttpClientErrorException.NotFound e) {
			return Collections.emptyList();
		}
	}

	private List<CustomerOrderDto> findWithTwoParameters(Object param1, String paramName1, Object param2,
			String paramName2, String path) {
		try {
			return restClient.get()
					.uri(uriBuilder -> uriBuilder.path("customerOrder/search/" + path).queryParam(paramName1, param1)
							.queryParam(paramName2, param2).build())
					.retrieve().body(new ParameterizedTypeReference<List<CustomerOrderDto>>() {
					});
		} catch (HttpClientErrorException.NotFound e) {
			return Collections.emptyList();
		}
	}

	private List<CustomerOrderDto> findWithThreeParameters(Object param1, String paramName1, Object param2,
			String paramName2, Object param3, String paramName3, String path) {
		try {
			return restClient.get()
					.uri(uriBuilder -> uriBuilder.path("customerOrder/search/" + path).queryParam(paramName1, param1)
							.queryParam(paramName2, param2).queryParam(paramName3, param3).build())
					.retrieve().body(new ParameterizedTypeReference<List<CustomerOrderDto>>() {
					});
		} catch (HttpClientErrorException.NotFound e) {
			return Collections.emptyList();
		}
	}

	// Helper used by your service / ObjectMapper post-processor
	public CustomerOrderDto withId(CustomerOrderDto coDto) {
		if (coDto == null)
			return null;

		Integer id = extractIdFromSelfLink(coDto);
		Integer customerId = extractCustomerId(coDto);
		Integer employeeId = extractEmployeeId(coDto);
		Integer shipperId = extractShipperId(coDto);
		Integer paymentTypeId = extractPaymentTypeId(coDto);
		Integer orderStatusId = extractOrderStatusId(coDto);
		Integer orderTaxStatusId = extractOrderTaxStatusId(coDto);

		CustomerDto cDto = null;
		if (customerId != null) { //
			List<CustomerDto> list = customerService.findById(customerId);
			cDto = list.size() > 0 ? list.get(0) : null;
			if (cDto != null) {
				Optional<CompanyDto> cOpt  = companyService.findById(extractIdForCompany(cDto));
				CompanyDto companyDto = cOpt.isPresent() ? cOpt.get() : null;
				if (companyDto != null) {
					cDto = new CustomerDto(cDto.id(),companyDto,cDto.lastName(),cDto.firstName(),cDto.emailAddress(),cDto.jobTitle(),cDto.jobTitle(),
							cDto.businessPhone(),cDto.homePhone(),cDto.faxNumber(),cDto.address1(),cDto.address2(),cDto.city(),cDto.stateProvince(),
							cDto.zipPostalCode(),cDto.countryRegion(),cDto.notes(),cDto.webSiteTitle(),cDto.webSiteUrl(),cDto.portraitPath(),cDto.portraitTitle(),
							cDto.links());
				}
			}
		}
		EmployeeDto eDto = null;
		if (employeeId != null) { //
			Optional<EmployeeDto> opt = employeeService.findById(employeeId);
			eDto = opt.isPresent() ? opt.get() : null;
			if (eDto != null) {
				Optional<CompanyDto> cOpt = companyService.findById(extractIdForCompany(eDto));
				CompanyDto companyDto = cOpt.isPresent() ? cOpt.get() : null;
				if (companyDto != null) {
					eDto = new EmployeeDto(eDto.id(),companyDto,eDto.lastName(),eDto.firstName(),eDto.emailAddress(),eDto.jobTitle(),eDto.jobTitle(),
							eDto.businessPhone(),eDto.homePhone(),eDto.faxNumber(),eDto.address1(),eDto.address2(),eDto.city(),eDto.stateProvince(),
							eDto.zipPostalCode(),eDto.countryRegion(),eDto.notes(),eDto.webSiteTitle(),eDto.webSiteUrl(),eDto.portraitPath(),eDto.portraitTitle(),
							eDto.links());
				}
			}
		}
		ShipperDto sDto = null;
		if (shipperId != null) { //
			List<ShipperDto> list = shipperService.findById(shipperId);
			sDto = list.size() > 0 ? list.get(0) : null;
			if (sDto != null) {
				Optional<CompanyDto> cOpt = companyService.findById(extractIdForCompany(sDto));
				CompanyDto companyDto = cOpt.isPresent() ? cOpt.get() : null;
				if (companyDto != null) {
					sDto = new ShipperDto(sDto.id(),companyDto,sDto.lastName(),sDto.firstName(),sDto.emailAddress(),sDto.jobTitle(),sDto.jobTitle(),
							sDto.businessPhone(),sDto.homePhone(),sDto.faxNumber(),sDto.address1(),sDto.address2(),sDto.city(),sDto.stateProvince(),
							sDto.zipPostalCode(),sDto.countryRegion(),sDto.notes(),sDto.webSiteTitle(),sDto.webSiteUrl(),sDto.portraitPath(),sDto.portraitTitle(),
							sDto.links());
				}
			}
		}
		PaymentTypeDto paymentTypeDto = null;
		if (paymentTypeId != null) { //
			List<PaymentTypeDto> list = paymentTypeService.findById(paymentTypeId);
			paymentTypeDto = list.size() > 0 ? list.get(0) : null;
		}
		OrderStatusDto orderStatusDto = null;
		if (orderStatusId != null) { //
			Optional<OrderStatusDto> opt = orderStatusService.findById(orderStatusId);
			orderStatusDto = opt.isPresent() ? opt.get() : null;
		}
		OrderTaxStatusDto orderTaxStatusDto = null;
		if (orderTaxStatusId != null) { //
			List<OrderTaxStatusDto> list = orderTaxStatusService.findById(orderTaxStatusId);
			orderTaxStatusDto = list.size() > 0 ? list.get(0) : null;
		}
		
		CustomerOrderDto custOrderDto = new CustomerOrderDto(id, eDto, cDto, sDto, coDto.orderDate(), coDto.shippedDate(),
				coDto.shipName(), coDto.shipAddress(), coDto.shipCity(), coDto.shipStateProvince(), coDto.shipZipPostalCode(),
				coDto.shipCountryRegion(), coDto.shippingFee(), coDto.taxes(), paymentTypeDto, coDto.paidDate(), coDto.notes(),
				coDto.taxRate(), orderTaxStatusDto, orderStatusDto, coDto.links());
		
		return custOrderDto;
	}

	private Integer extractIdFromSelfLink(CustomerOrderDto co) {
		if (co == null || co.links() == null)
			return null;
		return extractIdFromSelf(co.links().getLink("self"));
	}

	private Integer extractCustomerId(CustomerOrderDto co) {
		if (co == null || co.links() == null)
			return null;
		return extractIdFromLink(co.links().getLink("customer"));
	}

	private Integer extractEmployeeId(CustomerOrderDto co) {
		if (co == null || co.links() == null)
			return null;
		return extractIdFromLink(co.links().getLink("employee"));
	}

	private Integer extractShipperId(CustomerOrderDto co) {
		if (co == null || co.links() == null)
			return null;
		return extractIdFromLink(co.links().getLink("shipper"));
	}

	private Integer extractPaymentTypeId(CustomerOrderDto co) {
		if (co == null || co.links() == null)
			return null;
		return extractIdFromLink(co.links().getLink("paymentType"));
	}

	private Integer extractOrderStatusId(CustomerOrderDto co) {
		if (co == null || co.links() == null)
			return null;
		return extractIdFromLink(co.links().getLink("orderStatus"));
	}

	private Integer extractOrderTaxStatusId(CustomerOrderDto co) {
		if (co == null || co.links() == null)
			return null;
		return extractIdFromLink(co.links().getLink("orderTaxStatus"));
	}

	private Integer extractIdForCompany(CustomerDto c) {
		if (c == null || c.links() == null)
			return null;
		return extractIdFromLink(c.links().getLink("company"));
	}

	private Integer extractIdForCompany(EmployeeDto e) {
		if (e == null || e.links() == null)
			return null;
		return extractIdFromLink(e.links().getLink("company"));
	}

	private Integer extractIdForCompany(ShipperDto s) {
		if (s == null || s.links() == null)
			return null;
		return extractIdFromLink(s.links().getLink("company"));
	}

}