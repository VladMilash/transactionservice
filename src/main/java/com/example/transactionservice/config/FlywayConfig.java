package com.example.transactionservice.config;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

import javax.sql.DataSource;

@Configuration
public class FlywayConfig {

    private final Flyway flywayShard1;
    private final Flyway flywayShard2;

    public FlywayConfig(@Qualifier("datasource-shard1") DataSource dataSource1,
                        @Qualifier("datasource-shard2") DataSource dataSource2) {
        this.flywayShard1 = Flyway.configure()
                .dataSource(dataSource1)
                .locations("classpath:db/migration")
                .load();
        this.flywayShard2 = Flyway.configure()
                .dataSource(dataSource2)
                .locations("classpath:db/migration")
                .load();
    }

    @EventListener(ApplicationReadyEvent.class)
    public void migrateDatabasesShard1() {
        flywayShard1.migrate();
    }

    @EventListener(ApplicationReadyEvent.class)
    public void migrateDatabasesShard2() {
        flywayShard2.migrate();
    }
}