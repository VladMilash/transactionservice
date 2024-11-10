package com.example.transactionservice.config;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

import javax.sql.DataSource;

@Configuration
public class FlywayConfig {

    @Bean
    public Flyway flywayShard1(@Qualifier("datasource-shard1") DataSource dataSource) {
        return Flyway.configure()
                .dataSource(dataSource)
                .locations("classpath:db/migration")
                .load();
    }

    @Bean
    public Flyway flywayShard2(@Qualifier("datasource-shard2") DataSource dataSource) {
        return Flyway.configure()
                .dataSource(dataSource)
                .locations("classpath:db/migration")
                .load();
    }

    @EventListener(ApplicationReadyEvent.class)
    public void migrateDatabases(@Autowired Flyway flywayShard1, @Autowired Flyway flywayShard2) {
        flywayShard1.migrate();
        flywayShard2.migrate();
    }
}
