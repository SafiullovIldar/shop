package com.shop.demo.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public HikariDataSource dataSource () throws ClassNotFoundException {
        HikariConfig config = new HikariConfig();
        Class.forName("org.postgresql.Driver");
        config.setJdbcUrl("jdbc:postgresql://localhost/shop");
        config.setUsername("postgres");
        config.setPassword("12345");
        return new HikariDataSource(config);
    }
}
