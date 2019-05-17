package com.shop.demo.converter.dtotoentity;

import com.shop.demo.dto.CompanyDto;
import com.shop.demo.entity.Company;
import org.springframework.core.convert.converter.Converter;

public class CompanyDtoToCompany implements Converter<CompanyDto, Company> {
    @Override
    public Company convert(CompanyDto companyDto) {
        Company company = new Company();
        company.setId(companyDto.getId());
        company.setName(companyDto.getName());
        return company;
    }
}
