package com.example.transactionservice.config;

import org.apache.shardingsphere.driver.api.ShardingSphereDataSourceFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.*;

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

    @Bean
    public DataSource shardingDataSource(
            @Qualifier("datasource-shard1") DataSource shard1,
            @Qualifier("datasource-shard2") DataSource shard2
    ) throws SQLException {
        // Создаем карту источников данных
        Map<String, DataSource> dataSourceMap = new HashMap<>();
        dataSourceMap.put("shard1", shard1);
        dataSourceMap.put("shard2", shard2);

        // Настройки ShardingSphere
        Properties props = new Properties();
        props.setProperty("sql-show", "true");

        // Создаем ShardingSphereDataSource
        return ShardingSphereDataSourceFactory.createDataSource(dataSourceMap, Collections.emptyList(), props);
    }
}