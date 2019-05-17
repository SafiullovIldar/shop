package com.shop.demo.dao;

import com.shop.demo.entity.Company;
import org.springframework.stereotype.Component;

@Component
public interface CompanyDao {

    void createCompany(Company company);
    Company getCompanyById(String companyId);
}
