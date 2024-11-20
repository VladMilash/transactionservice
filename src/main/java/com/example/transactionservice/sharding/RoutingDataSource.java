package com.example.transactionservice.sharding;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

@Slf4j
public class RoutingDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        String shard = ShardContext.getCurrentShard();
        log.info("Routing to shard: " + shard);
        log.info("The thread is : " + Thread.currentThread().getName());
        return shard;
    }
}
