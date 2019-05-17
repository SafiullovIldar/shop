package com.shop.demo.converter.dtotoentity;

import com.shop.demo.dto.CustomerDto;
import com.shop.demo.entity.Customer;
import org.springframework.core.convert.converter.Converter;

public class CustomerDtoToCustomer implements Converter<CustomerDto, Customer> {

    @Override
    public Customer convert(CustomerDto customerDto) {
        Customer customer = new Customer();
        customer.setId(customerDto.getId());
        customer.setName(customerDto.getName());
        customer.setPhoneNumber(customerDto.getPhoneNumber());
        customer.setAddress(customerDto.getAddress());
        customer.setEmail(customerDto.getEmail());
        return customer;
    }
}
