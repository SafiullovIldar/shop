package com.shop.demo.dao;

import com.shop.demo.entity.Customer;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;

@Component
public interface CustomerDao {

    void createCustomer(Customer customer) throws SQLException;
    List<Customer> getAllCustomers();
    Customer getCustomerById(String customer_id);
}
