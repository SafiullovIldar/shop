package com.shop.demo.converter.entitytodto;

import com.shop.demo.dto.CustomerDto;
import com.shop.demo.entity.Customer;
import org.springframework.core.convert.converter.Converter;

public class CustomerToCustomerDto implements Converter<Customer, CustomerDto> {
    @Override
    public CustomerDto convert(Customer customer) {
        CustomerDto dto = new CustomerDto();
        dto.setId(customer.getId());
        dto.setName(customer.getName());
        dto.setAddress(customer.getAddress());
        dto.setEmail(customer.getEmail());
        dto.setPhoneNumber(customer.getPhoneNumber());
        return dto;
    }
}
