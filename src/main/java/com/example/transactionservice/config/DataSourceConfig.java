package com.example.transactionservice.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Bean
    @Qualifier("datasource-shard1")
    public DataSource dataSourceShard1(
            @Value("${spring.datasource-shard1.url}") String url,
            @Value("${spring.datasource-shard1.username}") String username,
            @Value("${spring.datasource-shard1.password}") String password
    ) {
        return DataSourceBuilder.create()
                .url(url)
                .username(username)
                .password(password)
                .build();
    }

    @Bean
    @Qualifier("datasource-shard2")
    public DataSource dataSourceShard2(
            @Value("${spring.datasource-shard2.url}") String url,
            @Value("${spring.datasource-shard2.username}") String username,
            @Value("${spring.datasource-shard2.password}") String password
    ) {
        return DataSourceBuilder.create()
                .url(url)
                .username(username)
                .password(password)
                .build();
    }
}