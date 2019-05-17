package com.shop.demo.dao.impl;

import com.shop.demo.dao.CompanyDao;
import com.shop.demo.entity.Company;
import com.zaxxer.hikari.HikariDataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CompanyDaoImpl implements CompanyDao {

    private final HikariDataSource dataSource;

    @Override
    public void createCompany(Company company) {

        try (Connection connection = dataSource.getConnection()) {

            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO company VALUES (?,?)");

            preparedStatement.setString(1, company.getId());
            preparedStatement.setString(2, company.getName());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Company getCompanyById(String companyId) {

        Company company = null;

        try (Connection connection = dataSource.getConnection()) {

            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM company WHERE id = ?");

            preparedStatement.setString(1, companyId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                company = new Company();
                company.setId(resultSet.getString("id"));
                company.setName(resultSet.getString("name"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return company;
    }
}
