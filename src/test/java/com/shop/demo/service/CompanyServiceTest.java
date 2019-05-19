package com.shop.demo.service;

import com.shop.demo.config.AppConfig;
import com.shop.demo.dao.CompanyDao;
import com.shop.demo.dto.CompanyDto;
import com.shop.demo.entity.Company;
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

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
@WebAppConfiguration
public class CompanyServiceTest {

    @Autowired
    private ConversionService conversionService;

    @Mock
    private CompanyDao companyDao;

    @Autowired
    @InjectMocks
    private CompanyService companyService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void whenCreateCompanyCalledVerified() {
        Company company = new Company("1", "TestCompany");
        CompanyDto companyDto = conversionService.convert(company, CompanyDto.class);

        doNothing().when(companyDao).createCompany(company);
        companyService.createCompany(companyDto);

        verify(companyDao, times(1)).createCompany(company);
    }

}