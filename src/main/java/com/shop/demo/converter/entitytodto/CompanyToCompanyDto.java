package com.shop.demo.converter.entitytodto;

import com.shop.demo.dto.CompanyDto;
import com.shop.demo.entity.Company;
import org.springframework.core.convert.converter.Converter;

public class CompanyToCompanyDto implements Converter<Company, CompanyDto> {

    @Override
    public CompanyDto convert(Company company) {
        CompanyDto dto = new CompanyDto();
        dto.setId(company.getId());
        dto.setName(company.getName());
        return dto;
    }
}
