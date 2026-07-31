package org.llin.demo.northwind.controller.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.llin.demo.northwind._Classes_EntityObject;
import org.llin.demo.northwind._Titles;
import org.llin.demo.northwind.dto.InventoryTransactionDto;
import org.llin.demo.northwind.model.entity.EntityObject;
import org.llin.demo.northwind.service.entity.CustomerOrderService;
import org.llin.demo.northwind.service.entity.InventoryTransactionService;
import org.llin.demo.northwind.service.entity.InventoryTransactionTypeService;
import org.llin.demo.northwind.service.entity.ProductService;
import org.llin.demo.northwind.service.entity.PurchaseOrderService;
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
@RequestMapping("/inventoryTransaction")
public class InventoryTransactionController<T extends EntityObject> extends _EntityController<T> implements _Classes_EntityObject, _Titles {
	
	private final InventoryTransactionService inventoryTransactionService;
	private final CustomerOrderService customerOrderService;	
	private final ProductService productService;
	private final PurchaseOrderService purchaseOrderService;
	private final InventoryTransactionTypeService inventoryTransactionTypeService;

	@Autowired
	public InventoryTransactionController(InventoryTransactionService inventoryTransactionService,
			CustomerOrderService customerOrderService, 
			ProductService productService,
			PurchaseOrderService purchaseOrderService, InventoryTransactionTypeService inventoryTransactionTypeService) {
		this.inventoryTransactionService = inventoryTransactionService;
		this.customerOrderService = customerOrderService;		
		this.productService = productService;
		this.purchaseOrderService = purchaseOrderService;
		this.inventoryTransactionTypeService = inventoryTransactionTypeService;
	}
		
	@GetMapping("/list")
	public ModelAndView getAllInventoryTransaction() {
		return createDefaultModelAndView();
	}

    @PostMapping
    public ResponseEntity<InventoryTransactionDto> create(@RequestBody InventoryTransactionDto dto) {    	
        return ResponseEntity.ok(inventoryTransactionService.create(dto));
    }
    
    @PutMapping
    public ResponseEntity<InventoryTransactionDto> update(@RequestBody InventoryTransactionDto dto) {
        return ResponseEntity.ok(inventoryTransactionService.update(dto.id(), dto));
    }

    @DeleteMapping
    public ResponseEntity<InventoryTransactionDto> delete(@RequestBody InventoryTransactionDto dto) {
    	inventoryTransactionService.deleteById(dto.id());
        return ResponseEntity.ok(dto);
    }
     

    @GetMapping("/{id}")
    public ModelAndView findById(@PathVariable Integer id) {
    	ModelAndView mav = createDefaultModelAndView();
        Optional<InventoryTransactionDto> inventoryTransactionOpt = inventoryTransactionService.findById(id);
        
        if (inventoryTransactionOpt.isPresent()) {
            mav.addObject(INVENTORY_TRANSACTION, inventoryTransactionOpt.get());
            mav.setViewName("inventoryTransactions/detail");
        } else {
            mav.setViewName("error/404");
        }
        
        return mav;      	
    }	
    
    @GetMapping("/search/findByInventoryTransactionTypeId?id={id}")
    public ModelAndView findByInventoryTransactionTypeId(@PathVariable Integer id) {
    	ModelAndView mv = createDefaultModelAndView();
    	mv.addObject(INVENTORY_TRANSACTION, inventoryTransactionService.findByInventoryTransactionTypeId(id));
    	return mv;
    }
    
    @GetMapping("/search/findByProductId?id={id}")
    public ModelAndView findByProductId(@PathVariable Integer id) {
    	ModelAndView mv = createDefaultModelAndView();
    	mv.addObject(INVENTORY_TRANSACTION, inventoryTransactionService.findByProductId(id));
    	return mv;
    }
    
    @GetMapping("/search/findByPurchaseOrderId?id={id}")
    public ModelAndView findByPurchaseOrderId(@PathVariable Integer id) {
    	ModelAndView mv = createDefaultModelAndView();
    	mv.addObject(INVENTORY_TRANSACTION, inventoryTransactionService.findByPurchaseOrderId(id));
    	return mv;
    }    
 
    @GetMapping("/search/findByCustomerOrderId?id={id}")
    public ModelAndView findByCustomerOrderId(@PathVariable Integer id) {
    	ModelAndView mv = createDefaultModelAndView();
    	mv.addObject(INVENTORY_TRANSACTION, inventoryTransactionService.findByCustomerOrderId(id));
    	return mv;
    }    

    @GetMapping("/search/findByCommentsContaining?comments={comments}")
    public ModelAndView findByCommentsContaining(@PathVariable String comments) {
    	ModelAndView mv = createDefaultModelAndView();
    	mv.addObject(INVENTORY_TRANSACTION, inventoryTransactionService.findByCommentsContaining(comments));
    	return mv;
    }    

    @GetMapping("/search/getInventoryTransactionsByTransactionCreatedDateBetween")
    public ModelAndView getInventoryTransactionsByTransactionCreatedDateBetween(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start, 
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
            
        ModelAndView mv = createDefaultModelAndView();
        
        List<InventoryTransactionDto> transactions = inventoryTransactionService
                .getInventoryTransactionsByTransactionCreatedDateBetween(start, end);
                
        mv.addObject(INVENTORY_TRANSACTION, transactions);
        return mv;
    }

    @GetMapping("/search/getInventoryTransactionsByTransactionModifiedDateBetween")
    public ModelAndView getInventoryTransactionsByTransactionModifiedDateBetween(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start, 
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
            
        ModelAndView mv = createDefaultModelAndView();
        
        List<InventoryTransactionDto> transactions = inventoryTransactionService
                .getInventoryTransactionsByTransactionCreatedDateBetween(start, end);
                
        mv.addObject(INVENTORY_TRANSACTION, transactions);
        return mv;
    }

    @GetMapping("/search/getInventoryTransactionsByQuantityBetween")
    public ModelAndView getInventoryTransactionsByQuantityBetween(
            @RequestParam Integer minQty, 
            @RequestParam Integer maxQty) {
            
        ModelAndView mv = createDefaultModelAndView();
        
        List<InventoryTransactionDto> transactions = inventoryTransactionService
                .getInventoryTransactionsByQuantityBetween(minQty, maxQty);
                
        mv.addObject(INVENTORY_TRANSACTION, transactions);
        return mv;
    }
        
	private ModelAndView createDefaultModelAndView() {
		loadMenu();
		ModelAndView mv = new ModelAndView(getModelAndView().getView());
		mv.addObject(INVENTORY_TRANSACTIONS,inventoryTransactionService.findAll());
		mv.addObject(CUSTOMER_ORDERS,customerOrderService.findAll());	
		mv.addObject(PRODUCTS,productService.findAll());
		mv.addObject(PURCHASE_ORDERS,purchaseOrderService.findAll());
		mv.addObject(INVENTORY_TRANSACTION_TYPES,inventoryTransactionTypeService.findAll());
		mv.addObject(TITLE, TITLE_INVENTORY_TRANSACTION); 
		mv.setViewName("entities/inventoryTransaction");
		return mv;		
	}
	
}

