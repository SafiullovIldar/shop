package com.shop.demo.service;

import com.shop.demo.dto.CustomerDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CustomerService {

    void createCustomer(CustomerDto dto);
    List<CustomerDto> getAllCustomers();
    CustomerDto getCustomerById(String customerId);

}
