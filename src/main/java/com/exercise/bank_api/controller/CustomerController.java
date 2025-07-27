package com.exercise.bank_api.controller;

import com.exercise.bank_api.dto.CustomerDetailsDto;
import com.exercise.bank_api.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDetailsDto> getCustomerById(@PathVariable Long id) {
        CustomerDetailsDto customerDetails = customerService.getCustomerDetails(id);
        return ResponseEntity.ok(customerDetails);
    }
}
