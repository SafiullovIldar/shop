package com.shop.demo.service.impl;

import com.shop.demo.service.CompanyService;
import com.shop.demo.dao.CompanyDao;
import com.shop.demo.dto.CompanyDto;
import com.shop.demo.entity.Company;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private CompanyDao companyDao;
    private ConversionService conversion;

    @Override
    public void createCompany(CompanyDto companyDto) {
        Company company = conversion.convert(companyDto, Company.class);
        companyDao.createCompany(company);
    }

    @Override
    public CompanyDto getCompanyById(String companyId) {
        Company company = companyDao.getCompanyById(companyId);
        CompanyDto companyDto = conversion.convert(company, CompanyDto.class);
        return companyDto;
    }
}
