package com.bobsusedbooks.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;

@Configuration
public class DataInitializer {

    @Autowired
    private DataSource dataSource;

    @Bean
    public CommandLineRunner initData() {
        return args -> {
            // First execute schema.sql to create tables
            // ResourceDatabasePopulator schemaPopulator = new ResourceDatabasePopulator();
            // schemaPopulator.addScript(new ClassPathResource("schema_oracle.sql"));
            // schemaPopulator.execute(dataSource);
            
            // // Then execute data.sql to populate tables
            // ResourceDatabasePopulator dataPopulator = new ResourceDatabasePopulator();
            // dataPopulator.addScript(new ClassPathResource("data_oracle.sql"));
            // dataPopulator.execute(dataSource);
            
            System.out.println("Database initialized with schema and sample data");
        };
    }
}
