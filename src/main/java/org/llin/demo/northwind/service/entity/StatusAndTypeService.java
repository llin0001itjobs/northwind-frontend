package org.llin.demo.northwind.service.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class StatusAndTypeService {

	private final InventoryTransactionTypeService ittService;
	private final OrderDetailStatusService odsService;
	private final OrderStatusService osService;
	private final OrderTaxStatusService otsService;
	private final PaymentTypeService ptService;
	private final PurchaseOrderStatusService posService;
	
    private final RestClient restClient;

    @Autowired
    public StatusAndTypeService(@Qualifier("northwindDataClient") RestClient restClient,
    		InventoryTransactionTypeService ittService,
    		OrderDetailStatusService odsService,
    		OrderStatusService osService,
    		OrderTaxStatusService otsService,
    		PaymentTypeService ptService,
    		PurchaseOrderStatusService posService) {
        this.restClient = restClient;
        this.ittService = ittService;
        this.odsService = odsService;
        this.osService = osService;
        this.otsService = otsService;
        this.ptService = ptService;
        this.posService = posService;
    }

	public InventoryTransactionTypeService getIttService() {
		return ittService;
	}

	public OrderDetailStatusService getOdsService() {
		return odsService;
	}

	public OrderStatusService getOsService() {
		return osService;
	}

	public OrderTaxStatusService getOtsService() {
		return otsService;
	}

	public PaymentTypeService getPtService() {
		return ptService;
	}

	public PurchaseOrderStatusService getPosService() {
		return posService;
	}

	
}
