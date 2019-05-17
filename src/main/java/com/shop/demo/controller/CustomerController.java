package com.shop.demo.controller;

import com.shop.demo.dto.CustomerDto;
import com.shop.demo.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("customer")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping()
    public void createCustomer(@RequestBody CustomerDto dto) {
        customerService.createCustomer(dto);
    }

    @GetMapping
    public List<CustomerDto> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{id}")
    public CustomerDto getCustomerById(@PathVariable("id") String customerId) {
        return customerService.getCustomerById(customerId);
    }
}
