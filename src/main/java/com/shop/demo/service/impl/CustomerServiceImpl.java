package com.shop.demo.service.impl;

import com.shop.demo.service.CustomerService;
import com.shop.demo.service.ShoppingCartService;
import com.shop.demo.dao.CustomerDao;
import com.shop.demo.dto.CustomerDto;
import com.shop.demo.dto.ShoppingCartDto;
import com.shop.demo.entity.Customer;
import com.shop.demo.transaction.TransactionalManager;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private CustomerDao customerDao;
    private ShoppingCartService shoppingCartService;
    private ConversionService conversion;
    private TransactionalManager transactionalManager;

    @Override
    public void createCustomer(CustomerDto dto) {
        Customer customer = conversion.convert(dto, Customer.class);
        if (dto.getId() == null) {
            customer.setId(UUID.randomUUID().toString());
        }

        transactionalManager.startTransaction();

        try {
            customerDao.createCustomer(customer);
            shoppingCartService.createShoppingCart(new ShoppingCartDto(), customer.getId());
            transactionalManager.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            transactionalManager.rollBack();
        }

    }

    @Override
    public List<CustomerDto> getAllCustomers() {
        List<Customer> allCustomers = customerDao.getAllCustomers();
        List<CustomerDto> customerDtos = new ArrayList<>();

        for (Customer allCustomer : allCustomers) {
            customerDtos.add(conversion.convert(allCustomer, CustomerDto.class));
        }

        return customerDtos;
    }

    @Override
    public CustomerDto getCustomerById(String customerId) {
        Customer customer = customerDao.getCustomerById(customerId);
        CustomerDto customerDto = conversion.convert(customer, CustomerDto.class );
        return customerDto;
    }
}
