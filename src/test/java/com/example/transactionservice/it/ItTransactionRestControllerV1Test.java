package com.example.transactionservice.it;

import com.example.transactionservice.config.PostgreTestcontainerConfig;
import com.example.transactionservice.service.TransactionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(PostgreTestcontainerConfig.class)
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class ItTransactionRestControllerV1Test {

    @Autowired
    private MockMvc mockMvc;

    private TransactionService transactionService;
    @BeforeEach
    void setup() {
    }


    @Test
    public void test() {
        Assertions.assertEquals(1 + 1, 2);
    }
}