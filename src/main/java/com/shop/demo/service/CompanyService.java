package com.shop.demo.service;

import com.shop.demo.dto.CompanyDto;
import org.springframework.stereotype.Service;

@Service
public interface CompanyService {

    void createCompany(CompanyDto companyDto);
    CompanyDto getCompanyById(String companyId);
}
