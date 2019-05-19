package com.shop.demo.controller;

import com.shop.demo.dto.CustomerDto;
import com.shop.demo.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("customer")
public class CustomerController {

    private CustomerService customerService;

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
