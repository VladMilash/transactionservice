package com.example.transactionservice.sharding;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

import static com.example.transactionservice.sharding.ShardDeterminer.SHARD_ONE;
import static com.example.transactionservice.sharding.ShardDeterminer.SHARD_TWO;

@Configuration
@EnableTransactionManagement
public class DatasourceConfig {
    @Value("${spring.datasource.shard1.url}")
    private String urlShardOne;

    @Value("${spring.datasource.shard1.username}")
    private String usernameShardOne;

    @Value("${spring.datasource.shard1.password}")
    private String passwordShardOne;

    @Value("${spring.datasource.shard2.url}")
    private String urlShardTwo;

    @Value("${spring.datasource.shard2.username}")
    private String usernameShardTwo;

    @Value("${spring.datasource.shard2.password}")
    private String passwordShardTwo;

    @Bean(name = "transactionServiceShardDataSourceOne")
    public DataSource dataSourceShard1() {
        return DataSourceBuilder.create()
                .url(urlShardOne)
                .username(usernameShardOne)
                .password(passwordShardOne)
                .build();
    }

    @Bean(name = "transactionServiceShardDataSourceTwo")
    public DataSource dataSourceShard2() {
        return DataSourceBuilder.create()
                .url(urlShardTwo)
                .username(usernameShardTwo)
                .password(passwordShardTwo)
                .build();
    }


    @Primary
    @Bean (name = "myRoutingDatasource")
    public DataSource routingDataSource() {
        Map<Object, Object> dataSources = new HashMap<>();
        dataSources.put(SHARD_ONE, dataSourceShard1());
        dataSources.put(SHARD_TWO, dataSourceShard2());

        RoutingDataSource routingDataSource = new RoutingDataSource();
        routingDataSource.setTargetDataSources(dataSources);
//        routingDataSource.setDefaultTargetDataSource(dataSourceShard1());
        return routingDataSource;
    }

    @Primary
    @Bean
    public DataSourceTransactionManager transactionManager(@Qualifier("myRoutingDatasource")DataSource routingDataSource) {
        return new DataSourceTransactionManager(routingDataSource);
    }

    @Primary
    @Bean(name = "myEntityManagerFactory")
    public EntityManagerFactory entityManagerFactory(@Qualifier("myRoutingDatasource") DataSource routingDataSource) {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(routingDataSource);
        factoryBean.setPackagesToScan("mentoring.transactionServiceApi");
        factoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        factoryBean.afterPropertiesSet();
        return factoryBean.getObject();
    }

    @Primary
    @Bean
    public EntityManager entityManager(@Qualifier("myEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return entityManagerFactory.createEntityManager();
    }

    @PostConstruct
    public void init() {
        ShardContext.setCurrentShard(SHARD_ONE);
    }
}
