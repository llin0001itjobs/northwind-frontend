package org.llin.demo.northwind.controller.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.llin.demo.northwind._Classes_EntityObject;
import org.llin.demo.northwind._Titles;
import org.llin.demo.northwind.dto.InventoryTransactionDto;
import org.llin.demo.northwind.model.entity._EntityObject;
import org.llin.demo.northwind.service.entity.InventoryTransactionService;
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
@RequestMapping("/entity/inventoryTransaction")
public class InventoryTransactionController<T extends _EntityObject> extends _EntityController<T> implements _Classes_EntityObject, _Titles {
	
	private final InventoryTransactionService inventoryTransactionService;

	@Autowired
	public InventoryTransactionController(InventoryTransactionService inventoryTransactionService) {
		this.inventoryTransactionService=inventoryTransactionService;
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
    	ModelAndView mv = createDefaultModelAndView();
        Optional<InventoryTransactionDto> inventoryTransactionOpt = inventoryTransactionService.findById(id);
        
        if (inventoryTransactionOpt.isPresent()) {
        	mv.addObject(INVENTORY_TRANSACTION, inventoryTransactionOpt.get());
        	mv.setViewName("inventoryTransactions/detail");
        } else {
        	mv.setViewName("error/404");
        }
        
        return mv;      	
    }	
    
    @GetMapping("/search/findByInventoryTransactionTypeId?id={id}")
    public ModelAndView findByInventoryTransactionTypeId(@PathVariable Integer id) {
    	ModelAndView mv = createDefaultModelAndView();
    	List<InventoryTransactionDto> transactions = inventoryTransactionService.findByInventoryTransactionTypeId(id);
    	mv.addObject(INVENTORY_TRANSACTION, enrichAll(transactions));

    	return mv;
    }
    
    @GetMapping("/search/findByProductId?id={id}")
    public ModelAndView findByProductId(@PathVariable Integer id) {
    	ModelAndView mv = createDefaultModelAndView();
    	List<InventoryTransactionDto> transactions = inventoryTransactionService.findByProductId(id);
    	mv.addObject(INVENTORY_TRANSACTION, enrichAll(transactions));

    	return mv;
    }
    
    @GetMapping("/search/findByPurchaseOrderId?id={id}")
    public ModelAndView findByPurchaseOrderId(@PathVariable Integer id) {
    	ModelAndView mv = createDefaultModelAndView();
    	List<InventoryTransactionDto> transactions = inventoryTransactionService.findByPurchaseOrderId(id);
    	mv.addObject(INVENTORY_TRANSACTION, enrichAll(transactions));
    	return mv;
    }    
 
    @GetMapping("/search/findByCustomerOrderId?id={id}")
    public ModelAndView findByCustomerOrderId(@PathVariable Integer id) {
    	ModelAndView mv = createDefaultModelAndView();
        List<InventoryTransactionDto> transactions = inventoryTransactionService.
        		findByCustomerOrderId(id);
    	mv.addObject(INVENTORY_TRANSACTION, enrichAll(transactions));
    	return mv;
    }    

    @GetMapping("/search/findByCommentsContaining?comments={comments}")
    public ModelAndView findByCommentsContaining(@PathVariable String comments) {
    	ModelAndView mv = createDefaultModelAndView();
    	
        List<InventoryTransactionDto> transactions = inventoryTransactionService
                .findByCommentsContaining(comments);
        
    	mv.addObject(INVENTORY_TRANSACTION, enrichAll(transactions));
    	return mv;
    }    

    @GetMapping("/search/getInventoryTransactionsByTransactionCreatedDateBetween")
    public ModelAndView getInventoryTransactionsByTransactionCreatedDateBetween(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start, 
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
            
        ModelAndView mv = createDefaultModelAndView();
        
        List<InventoryTransactionDto> transactions = inventoryTransactionService
                .getInventoryTransactionsByTransactionCreatedDateBetween(start, end);
                
        mv.addObject(INVENTORY_TRANSACTION, enrichAll(transactions));
        return mv;
    }

    @GetMapping("/search/getInventoryTransactionsByTransactionModifiedDateBetween")
    public ModelAndView getInventoryTransactionsByTransactionModifiedDateBetween(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start, 
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
            
        ModelAndView mv = createDefaultModelAndView();
        
        List<InventoryTransactionDto> transactions = inventoryTransactionService
                .getInventoryTransactionsByTransactionCreatedDateBetween(start, end);
                
        mv.addObject(INVENTORY_TRANSACTION, enrichAll(transactions));
        return mv;
    }

    @GetMapping("/search/getInventoryTransactionsByQuantityBetween")
    public ModelAndView getInventoryTransactionsByQuantityBetween(
            @RequestParam Integer minQty, 
            @RequestParam Integer maxQty) {
            
        ModelAndView mv = createDefaultModelAndView();
        
        List<InventoryTransactionDto> transactions = inventoryTransactionService
                .getInventoryTransactionsByQuantityBetween(minQty, maxQty);
                
        mv.addObject(INVENTORY_TRANSACTION, enrichAll(transactions));
        return mv;
    }
        
	private ModelAndView createDefaultModelAndView() {
		loadMenu();
		ModelAndView mv = new ModelAndView(getModelAndView().getView());
		mv.addObject(INVENTORY_TRANSACTIONS, enrichAll(inventoryTransactionService.findAll()));
		mv.addObject("activeMenuSection", "fourthOrder");
		mv.addObject("currentEntityPath", "inventoryTransaction");
		mv.addObject(TITLE, TITLE_INVENTORY_TRANSACTION); 
		mv.setViewName("entity/inventoryTransaction");
		return mv;		
	}
	
	private List<InventoryTransactionDto> enrichAll(List<InventoryTransactionDto> inventoryTransactions) {
		
		if (inventoryTransactions == null || inventoryTransactions.isEmpty())
			return List.of();
	    return inventoryTransactions.stream()
	            .map(inventoryTransaction -> {	    
	            	try {
	                    return inventoryTransactionService.enrich(inventoryTransaction);
	                } catch (Exception e) {
	                    // Force a print statements to capture what Eclipse hides
	                    System.err.println("CRITICAL: Failed to enrich order ID " + inventoryTransaction.id() + " -> " + e.getMessage());
	                    return inventoryTransaction; // Return raw, un-enriched DTO so the page still loads!
	                }
	            })
	            .toList();
	}
	
}

