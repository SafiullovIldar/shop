package com.shop.demo.service;

import com.shop.demo.config.AppConfig;
import com.shop.demo.dao.CustomerDao;
import com.shop.demo.dto.CustomerDto;
import com.shop.demo.entity.Customer;
import com.shop.demo.util.Util;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.sql.SQLException;
import java.util.Collections;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
@WebAppConfiguration
public class CustomerServiceTest {

    @Autowired
    ConversionService conversionService;

    @Mock
    private CustomerDao customerDao;

    @Autowired
    @InjectMocks
    private CustomerService customerService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void whenCreateCustomerCalledVerified() throws SQLException {
        CustomerDto customerDto = Util.createTestCustomer("1");
        Customer customer = conversionService.convert(customerDto, Customer.class);

        doNothing().when(customerDao).createCustomer(customer);
        customerService.createCustomer(customerDto);

        verify(customerDao, times(1)).createCustomer(customer);
    }

    @Test
    public void shouldGetExpectedCustomersCountAndFields() {
        when(customerDao.getAllCustomers()).
                thenReturn(
                        Collections.singletonList(
                                new Customer("1",
                                        "TestName",
                                        "TestEmail",
                                        "TestAddress",
                                        "TestPhone")));

        assertEquals(1, customerService.getAllCustomers().size());
        assertEquals("1", customerService.getAllCustomers().get(0).getId());
        assertEquals("TestName", customerService.getAllCustomers().get(0).getName());
        assertEquals("TestEmail", customerService.getAllCustomers().get(0).getEmail());
        assertEquals("TestAddress", customerService.getAllCustomers().get(0).getAddress());
        assertEquals("TestPhone", customerService.getAllCustomers().get(0).getPhoneNumber());
    }
}