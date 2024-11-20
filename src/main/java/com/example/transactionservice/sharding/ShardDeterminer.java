package com.example.transactionservice.sharding;

import java.util.UUID;

public class ShardDeterminer {
    public static final String SHARD_ONE = "shard1";
    public static final String SHARD_TWO = "shard2";

    public static String determineShardKey(UUID userId) {
        return (userId.hashCode() % 2 == 0) ? SHARD_ONE : SHARD_TWO;
    }
}
