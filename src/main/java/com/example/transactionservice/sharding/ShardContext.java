package com.example.transactionservice.sharding;

import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

import static com.example.transactionservice.sharding.ShardDeterminer.determineShardKey;

@Slf4j
public class ShardContext {
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

    public static void setCurrentShard(String shardKey) {
        contextHolder.set(shardKey);
    }

    public static String getCurrentShard() {
        return contextHolder.get();
    }

    public static void clear() {
        log.info("Current shard before clear : " + ShardContext.getCurrentShard());
        contextHolder.remove();
    }

    public static void determineAndSetShard(String user_uid) {
        determineAndSetShard(UUID.fromString(user_uid));
    }

    public static void determineAndSetShard(UUID user_uid) {
        String shardKey = determineShardKey(user_uid);
        log.info("The selected shard is : " + shardKey);
        log.info("The thread is : " + Thread.currentThread().getName());
        ShardContext.setCurrentShard(shardKey);
    }
}
