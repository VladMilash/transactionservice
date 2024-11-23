package com.example.transactionservice;

import com.example.transactionservice.sharding.ShardContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class TransactionserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TransactionserviceApplication.class, args);
    }

}
