package com.example.transactionservice.flywayConfig;

import lombok.extern.slf4j.Slf4j;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Slf4j
@Configuration
public class FlywayConfig {
    @Bean(initMethod = "migrate")
    public Flyway flywayShard1(@Qualifier("transactionServiceShardDataSourceOne") DataSource transactionServiceShardDataSourceOne) {
        log.info("Shard 1 DataSource: " + transactionServiceShardDataSourceOne);
        return Flyway.configure()
                .dataSource(transactionServiceShardDataSourceOne)
                .locations("classpath:db/migration")
                .load();
    }

    @Bean(initMethod = "migrate")
    public Flyway flywayShard2(@Qualifier("transactionServiceShardDataSourceTwo") DataSource transactionServiceShardDataSourceTwo) {
        log.info("Shard 2 DataSource: " + transactionServiceShardDataSourceTwo);
        return Flyway.configure()
                .dataSource(transactionServiceShardDataSourceTwo)
                .locations("classpath:db/migration")
                .load();
    }
}
