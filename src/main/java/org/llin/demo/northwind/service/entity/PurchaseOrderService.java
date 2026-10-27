package org.llin.demo.northwind.service.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.llin.demo.northwind.dto.CompanyDto;
import org.llin.demo.northwind.dto.EmployeeDto;
import org.llin.demo.northwind.dto.OrderStatusDto;
import org.llin.demo.northwind.dto.PurchaseOrderDto;
import org.llin.demo.northwind.dto.SupplierDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

@Service
public class PurchaseOrderService extends _BaseService {
	private final RestClient restClient;

	private final CompanyService companyService;
	private final EmployeeService employeeService;
	private final OrderStatusService orderStatusService;
	private final SupplierService supplierService;

	@Autowired
	public PurchaseOrderService(@Qualifier("northwindDataClient") RestClient restClient, CompanyService companyService,
			EmployeeService employeeService, OrderStatusService orderStatusService, SupplierService supplierService) {
		this.restClient = restClient;
		this.companyService = companyService;
		this.employeeService = employeeService;
		this.orderStatusService = orderStatusService;
		this.supplierService = supplierService;
	}

	// ==================================================================
	// Helper classes for HAL _embedded wrapper (placed at class level)
	// ==================================================================

	private static class EmbeddedPurchaseOrders {
		@com.fasterxml.jackson.annotation.JsonProperty("_embedded")
		public PurchaseOrderList purchaseOrders;
	}

	private static class PurchaseOrderList {
		@com.fasterxml.jackson.annotation.JsonProperty("purchaseOrders")
		public List<PurchaseOrderDto> purchaseOrders;
	}

	// ==================================================================
	// Public methods
	// ==================================================================

	public Optional<PurchaseOrderDto> findById(Integer id) {
		if (id == null)
			return Optional.empty();

		return Optional
				.ofNullable(restClient.get().uri("purchaseOrder/{id}", id).retrieve().body(PurchaseOrderDto.class));
	}

	/**
	 * GET /purchaseOrder (returns all PurchaseOrders)
	 */
	public List<PurchaseOrderDto> findAll() {
		EmbeddedPurchaseOrders response = restClient.get().uri("purchaseOrder").retrieve()
				.body(EmbeddedPurchaseOrders.class);

		return response != null && response.purchaseOrders != null && response.purchaseOrders.purchaseOrders != null
				? response.purchaseOrders.purchaseOrders
				: List.of();
	}

	public PurchaseOrderDto create(PurchaseOrderDto PurchaseOrderDto) {
		return restClient.post().uri("purchaseOrder").body(PurchaseOrderDto).retrieve().body(PurchaseOrderDto.class);
	}

	public PurchaseOrderDto update(Integer id, PurchaseOrderDto PurchaseOrderDto) {
		return restClient.put().uri("purchaseOrder/{id}", id).body(PurchaseOrderDto).retrieve()
				.body(PurchaseOrderDto.class);
	}

	public void deleteById(Integer id) {
		restClient.delete().uri("purchaseOrder/{id}", id).retrieve().toBodilessEntity();
	}

	public List<PurchaseOrderDto> findBySupplierId(Integer id) {
		return findByObject(id, "id", "findBySupplierId");
	}

	public List<PurchaseOrderDto> findByCreatedById(Integer id) {
		return findByObject(id, "id", "findByCreatedById");
	}

	public List<PurchaseOrderDto> findByApprovedById(Integer id) {
		return findByObject(id, "id", "findByApprovedById");
	}

	public List<PurchaseOrderDto> findBySubmittedById(Integer id) {
		return findByObject(id, "id", "findBySubmittedById");
	}

	public List<PurchaseOrderDto> findByOrderStatusId(Integer id) {
		return findByObject(id, "id", "findByOrderStatusId");
	}

	// Date ranges (very common for PO reports)
	public List<PurchaseOrderDto> findByCreationDateBetweenOrderByCreationDateAsc(LocalDateTime start,
			LocalDateTime end) {
		return findWithTwoParameters(start, "start", end, "end", "findByCreationDateBetweenOrderByCreationDateAsc");
	}

	public List<PurchaseOrderDto> findBySubmittedDateBetweenOrderBySubmittedDateAsc(LocalDateTime start,
			LocalDateTime end) {
		return findWithTwoParameters(start, "start", end, "end", "findBySubmittedDateBetweenOrderBySubmittedDateAsc");
	}

	public List<PurchaseOrderDto> findByExpectedDateBetweenOrderByExpectedDateAsc(LocalDateTime start,
			LocalDateTime end) {
		return findWithTwoParameters(start, "start", end, "end", "findByExpectedDateBetweenOrderByExpectedDateAsc");
	}

	public List<PurchaseOrderDto> findByPaymentDateBetweenOrderByPaymentDateAsc(LocalDateTime start,
			LocalDateTime end) {
		return findWithTwoParameters(start, "start", end, "end", "findByPaymentDateBetweenOrderByPaymentDateAsc");
	}

	// Money ranges
	public List<PurchaseOrderDto> findByShippingFeeBetweenOrderByShippingFeeAsc(BigDecimal min, BigDecimal max) {
		return findWithTwoParameters(min, "min", max, "max", "findByShippingFeeBetweenOrderByShippingFeeAsc");
	}

	public List<PurchaseOrderDto> findByTaxesBetweenOrderByTaxesAsc(BigDecimal min, BigDecimal max) {
		return findWithTwoParameters(min, "min", max, "max", "findByTaxesBetweenOrderByTaxesAsc");
	}

	public List<PurchaseOrderDto> findByPaymentAmountBetweenOrderByPaymentAmountAsc(BigDecimal min, BigDecimal max) {
		return findWithTwoParameters(min, "min", max, "max", "findByPaymentAmountBetweenOrderByPaymentAmountAsc");
	}

	public List<PurchaseOrderDto> findByNotesContaining(String notes) {
		return findByObject(notes, "notes", "findByNotesContaining");
	}

	private List<PurchaseOrderDto> findByObject(Object value, String paramName, String searchMethod) {
		if (value == null) {
			return Collections.emptyList();
		}

		try {
			return restClient.get()
					.uri(uriBuilder -> uriBuilder.path("purchaseOrder/search/{method}").queryParam(paramName, value)
							.build(searchMethod))
					.retrieve().body(new ParameterizedTypeReference<List<PurchaseOrderDto>>() {
					});
		} catch (HttpClientErrorException.NotFound e) {
			return Collections.emptyList();
		}
	}

	private List<PurchaseOrderDto> findWithTwoParameters(Object param1, String paramName1, Object param2,
			String paramName2, String path) {
		try {
			return restClient.get()
					.uri(uriBuilder -> uriBuilder.path("purchaseOrder/search/" + path).queryParam(paramName1, param1)
							.queryParam(paramName2, param2).build())
					.retrieve().body(new ParameterizedTypeReference<List<PurchaseOrderDto>>() {
					});
		} catch (HttpClientErrorException.NotFound e) {
			return Collections.emptyList();
		}
	}

	// Helper used by your service / ObjectMapper post-processor

	public PurchaseOrderDto enrich(PurchaseOrderDto dto) {
	    if (dto == null) return null;

	    Integer id = extractIdFromSelfLink(dto);  // prefer link-based ID
	    
	    Optional<EmployeeDto> abOpt = employeeService.findById(extractApprovedById(dto));
	    Optional<EmployeeDto> cbOpt = employeeService.findById(extractCreatedById(dto));
	    Optional<EmployeeDto> sbOpt = employeeService.findById(extractSubmittedById(dto));
	    Optional<OrderStatusDto> osOpt = orderStatusService.findById(extractOrderStatusId(dto));
	    Optional<SupplierDto> suOpt = supplierService.findById(extractSupplierId(dto));
	    
	    EmployeeDto approvedBy = enrichWithCompany(abOpt.orElse(null));
	    EmployeeDto createdBy  = enrichWithCompany(cbOpt.orElse(null));
	    EmployeeDto submittedBy = enrichWithCompany(sbOpt.orElse(null));
	    OrderStatusDto orderStatus = osOpt.orElse(null);
	    SupplierDto supplier = enrichWithCompany(suOpt.orElse(null));
			    
	    return new PurchaseOrderDto(
	        id != null ? id : dto.id(),   // fallback if extraction fails
	        approvedBy,
	        createdBy,
	        submittedBy,
	        supplier,
	        orderStatus,
	        dto.approvedDate(),
	        dto.submittedDate(),
	        dto.creationDate(),
	        dto.expectedDate(),
	        dto.shippingFee(),
	        dto.taxes(),
	        dto.paymentDate(),
	        dto.paymentAmount(),
	        dto.paymentMethod(),
	        dto.notes(),
	        dto.links()
	    );
	}

	private Integer extractApprovedById(PurchaseOrderDto p) {
		if (p == null || p.links() == null)
			return null;
		return extractIdFromLink(p.links().getLink("approvedBy"));
	}

	private Integer extractCreatedById(PurchaseOrderDto p) {
		if (p == null || p.links() == null)
			return null;
		return extractIdFromLink(p.links().getLink("createdBy"));
	}

	private Integer extractSubmittedById(PurchaseOrderDto p) {
		if (p == null || p.links() == null)
			return null;
		return extractIdFromLink(p.links().getLink("submittedBy"));
	}

	private Integer extractOrderStatusId(PurchaseOrderDto p) {
		if (p == null || p.links() == null)
			return null;
		return extractIdFromLink(p.links().getLink("orderStatus"));
	}

	private Integer extractSupplierId(PurchaseOrderDto p) {
		if (p == null || p.links() == null)
			return null;
		return extractIdFromLink(p.links().getLink("supplier"));
	}

	private Integer extractIdFromSelfLink(PurchaseOrderDto p) {
		if (p == null || p.links() == null)
			return null;
		return extractIdFromSelf(p.links().getLink("self"));
	}

	private EmployeeDto enrichWithCompany(EmployeeDto dto) {
		if (dto != null) {
			if (Integer.valueOf(dto.id()) != null) { //
				Optional<CompanyDto> cOpt = companyService.findById(extractIdForCompany(dto));
				CompanyDto companyDto = cOpt.isPresent() ? cOpt.get() : null;
				if (companyDto != null) {
					dto = new EmployeeDto(dto.id(), companyDto, dto.lastName(), dto.firstName(), dto.emailAddress(),
							dto.jobTitle(), dto.jobTitle(), dto.businessPhone(), dto.homePhone(), dto.faxNumber(),
							dto.address1(), dto.address2(), dto.city(), dto.stateProvince(), dto.zipPostalCode(),
							dto.countryRegion(), dto.notes(), dto.webSiteTitle(), dto.webSiteUrl(), dto.portraitPath(),
							dto.portraitTitle(), dto.links());
				}
			}
		}
		return dto;
	}

	private SupplierDto enrichWithCompany(SupplierDto dto) {
		if (dto != null) {
			if (Integer.valueOf(dto.id()) != null) { //
				Optional<CompanyDto> cOpt = companyService.findById(extractIdForCompany(dto));
				CompanyDto companyDto = cOpt.isPresent() ? cOpt.get() : null;
				if (companyDto != null) {
					dto = new SupplierDto(dto.id(), companyDto, null, dto.lastName(), dto.firstName(),
							dto.emailAddress(), dto.jobTitle(), dto.jobTitle(), dto.businessPhone(), dto.homePhone(),
							dto.faxNumber(), dto.address1(), dto.address2(), dto.city(), dto.stateProvince(),
							dto.zipPostalCode(), dto.countryRegion(), dto.notes(), dto.webSiteTitle(), dto.webSiteUrl(),
							dto.portraitPath(), dto.portraitTitle(), dto.links());
				}
			}
		}
		return dto;
	}

	private Integer extractIdForCompany(EmployeeDto e) {
		if (e == null || e.links() == null)
			return null;
		return extractIdFromLink(e.links().getLink("company"));
	}

	private Integer extractIdForCompany(SupplierDto p) {
		if (p == null || p.links() == null)
			return null;
		return extractIdFromLink(p.links().getLink("company"));
	}
}
