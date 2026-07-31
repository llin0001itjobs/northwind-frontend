package org.llin.demo.northwind.controller.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.llin.demo.northwind._Classes_EntityObject;
import org.llin.demo.northwind._Titles;
import org.llin.demo.northwind.dto.InvoiceDto;
import org.llin.demo.northwind.model.entity.EntityObject;
import org.llin.demo.northwind.service.entity.CustomerOrderService;
import org.llin.demo.northwind.service.entity.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/invoice")
public class InvoiceController<T extends EntityObject> extends _EntityController<T> implements _Classes_EntityObject, _Titles {

	private final InvoiceService invoiceService;
	private final CustomerOrderService customerOrderService;

	@Autowired
	public InvoiceController(InvoiceService invoiceService,
			CustomerOrderService customerOrderService) {
		this.invoiceService = invoiceService;
		this.customerOrderService = customerOrderService;
	}
	
	@GetMapping("/list")
	public ModelAndView getAllInvoices() {
		return createDefaultModelAndView();
	}

    @PostMapping
    public ResponseEntity<InvoiceDto> create(@RequestBody InvoiceDto dto) {    	
        return ResponseEntity.ok(invoiceService.create(dto));
    }
    
    @PutMapping
    public ResponseEntity<InvoiceDto> update(@RequestBody InvoiceDto dto) {
        return ResponseEntity.ok(invoiceService.update(dto.id(), dto));
    }

    @DeleteMapping
    public ResponseEntity<InvoiceDto> delete(@RequestBody InvoiceDto dto) {
    	invoiceService.deleteById(dto.id());
        return ResponseEntity.ok(dto);
    }
     

    @GetMapping("/{id}")
    public ModelAndView findById(@PathVariable Integer id) {
    	ModelAndView mav = createDefaultModelAndView();
        Optional<InvoiceDto> invoiceOpt = invoiceService.findById(id);
        
        if (invoiceOpt.isPresent()) {
            mav.addObject(INVOICE, invoiceOpt.get());
            mav.setViewName("invoices/detail");
        } else {
            mav.setViewName("error/404");
        }
        
        return mav;      	
    }	

    @GetMapping("/search/findByDueDateBetweenOrderByDueDateAsc")
    public ModelAndView findByDueDateBetweenOrderByDueDateAsc(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dueDate1, 
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dueDate2) {
            
        ModelAndView mv = createDefaultModelAndView();
        
        List<InvoiceDto> invoices = invoiceService.findByDueDateBetweenOrderByDueDateAsc(dueDate1, dueDate2);
                
        mv.addObject(INVOICES, invoices);
        return mv;
    }
    
    @GetMapping("/search/findByInvoiceDateBetweenOrderByInvoiceDateAsc")
    public ModelAndView findByInvoiceDateBetweenOrderByInvoiceDateAsc(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime invoiceDate1, 
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime invoiceDate2) {
            
        ModelAndView mv = createDefaultModelAndView();
        
        List<InvoiceDto> invoices = invoiceService.findByInvoiceDateBetweenOrderByInvoiceDateAsc(invoiceDate1, invoiceDate2);
                
        mv.addObject(INVOICES, invoices);
        return mv;
    }

    @GetMapping("/search/findByAmountDueBetweenOrderByAmountDueAsc")
    public ModelAndView findByAmountDueBetweenOrderByAmountDueAsc(
            @RequestParam BigDecimal amountDue1, 
            @RequestParam  BigDecimal amountDue2) {
            
        ModelAndView mv = createDefaultModelAndView();
        
        List<InvoiceDto> invoices = invoiceService.findByAmountDueBetweenOrderByAmountDueAsc(amountDue1, amountDue2);
                
        mv.addObject(INVOICES, invoices);
        return mv;
    }
    
    @GetMapping("/search/findByShippingBetweenOrderByShippingAsc")
    public ModelAndView findByShippingBetweenOrderByShippingAsc(@RequestParam BigDecimal shipping1, @RequestParam  BigDecimal shipping2) {
            
        ModelAndView mv = createDefaultModelAndView();
        
        List<InvoiceDto> invoices = invoiceService.findByShippingBetweenOrderByShippingAsc(shipping1, shipping2);
                
        mv.addObject(INVOICES, invoices);
        return mv;
    }

    @GetMapping("/search/findByTaxBetweenOrderByTaxAsc")
    public ModelAndView findByTaxBetweenOrderByTaxAsc(@RequestParam BigDecimal tax1, @RequestParam  BigDecimal tax2) {
            
        ModelAndView mv = createDefaultModelAndView();
        
        List<InvoiceDto> invoices = invoiceService.findByTaxBetweenOrderByTaxAsc(tax1, tax2);
                
        mv.addObject(INVOICES, invoices);
        return mv;
    }
          
    @GetMapping("/search/findByCustomerOrderId")
    public ModelAndView findByCustomerOrderId(@PathVariable Integer id) {
    	ModelAndView mv = createDefaultModelAndView();
    	List<InvoiceDto> invoices = invoiceService.findByCustomerOrderId(id);
    	mv.addObject(INVOICES, invoices);
        return mv;      	
    }	   

    @GetMapping("/search/findByInvoiceDateAfterOrderByInvoiceDateAsc")
    public ModelAndView findByInvoiceDateAfterOrderByInvoiceDateAsc(
    		@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date) {
    	ModelAndView mv = createDefaultModelAndView();
    	List<InvoiceDto> invoices = invoiceService.findByInvoiceDateAfterOrderByInvoiceDateAsc(date);
    	mv.addObject(INVOICES, invoices);
        return mv;      	
    }	   

    @GetMapping("/search/findByDueDateBeforeOrderByDueDateAsc")
    public ModelAndView findByDueDateBeforeOrderByDueDateAsc(
    		@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date) {
    	ModelAndView mv = createDefaultModelAndView();
    	List<InvoiceDto> invoices = invoiceService.findByDueDateBeforeOrderByDueDateAsc(date);
    	mv.addObject(INVOICES, invoices);
        return mv;      	
    }	   

    @GetMapping("/search/findByDueDateBeforeAndAmountDueGreaterThanOrderByDueDateAsc")
    public ModelAndView findByDueDateBeforeAndAmountDueGreaterThanOrderByDueDateAsc(
    		@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date,
    		@RequestParam BigDecimal amountDue) {
    	ModelAndView mv = createDefaultModelAndView();
    	List<InvoiceDto> invoices = invoiceService.findByDueDateBeforeAndAmountDueGreaterThanOrderByDueDateAsc(date, amountDue);
    	mv.addObject(INVOICES, invoices);
        return mv;      	
    }	   
    
    @GetMapping("/search/findByInvoiceDateBetweenAndAmountDueGreaterThanOrderByInvoiceDateAsc")
    public ModelAndView findByInvoiceDateBetweenAndAmountDueGreaterThanOrderByInvoiceDateAsc(
    		@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
    		@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate,
    		@RequestParam BigDecimal minAmount) {
    	ModelAndView mv = createDefaultModelAndView();
    	List<InvoiceDto> invoices = invoiceService.findByInvoiceDateBetweenAndAmountDueGreaterThanOrderByInvoiceDateAsc(startDate, endDate, minAmount);
    	mv.addObject(INVOICES, invoices);
        return mv;      	
    }       

    @GetMapping("/search/findByAmountDueGreaterThanOrderByAmountDueDesc")
    public ModelAndView findByAmountDueGreaterThanOrderByAmountDueDesc(
    		@RequestParam BigDecimal amount) {
    	ModelAndView mv = createDefaultModelAndView();
    	List<InvoiceDto> invoices = invoiceService.findByAmountDueGreaterThanOrderByAmountDueDesc(amount);
    	mv.addObject(INVOICES, invoices);
        return mv;      	
    }       
    
    @GetMapping("/search/findByAmountDueLessThanOrderByAmountDueAsc")
    public ModelAndView findByAmountDueLessThanOrderByAmountDueAsc(
    		@RequestParam BigDecimal amount) {
    	ModelAndView mv = createDefaultModelAndView();
    	List<InvoiceDto> invoices = invoiceService.findByAmountDueLessThanOrderByAmountDueAsc(amount);
    	mv.addObject(INVOICES, invoices);
        return mv;      	
    }
        
	private ModelAndView createDefaultModelAndView() {
		loadMenu();
		ModelAndView mv = new ModelAndView(getModelAndView().getView());
		mv.addObject(INVOICES,        invoiceService.findAll());
		mv.addObject(CUSTOMER_ORDERS, customerOrderService.findAll());	
		mv.addObject(TITLE, TITLE_INVOICE);
		mv.setViewName("entities/invoice");
		return mv;		
	}
}


