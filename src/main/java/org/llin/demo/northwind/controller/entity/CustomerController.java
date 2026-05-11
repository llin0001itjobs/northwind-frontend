package org.llin.demo.northwind.controller.entity;

import java.util.List;

import org.llin.demo.northwind.dto.CustomerDto;
import org.llin.demo.northwind.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getById(@PathVariable Integer id) {
        return customerService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<CustomerDto> getAll() {
        return customerService.findAll();
    }

    @PostMapping
    public ResponseEntity<CustomerDto> create(@RequestBody CustomerDto dto) {
        return ResponseEntity.ok(customerService.create(dto));
    }
}