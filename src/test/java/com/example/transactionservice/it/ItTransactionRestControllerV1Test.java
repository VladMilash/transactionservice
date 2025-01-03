package com.example.transactionservice.it;

import com.example.transactionservice.config.PostgreTestcontainerConfig;
import com.example.transactionservice.service.WalletTypeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(PostgreTestcontainerConfig.class)
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class ItTransactionRestControllerV1Test {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WalletTypeService walletTypeService;

    @BeforeEach
    void setup() {
    }

    @Test
    public void testWalletTypeCreate_Success() throws Exception {
        var requestBuilder = post("/api/v1/wallets/type")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                             "name": "John",
                             "currencyCode": "123",
                             "status": "ACTIVE"          
                        }
                        """);

        this.mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpectAll(
                        status().is2xxSuccessful(),
                        content().contentType(MediaType.APPLICATION_JSON)
                );
    }

    @Test
    public void testWalletTypeGetAll() throws Exception {
        var requestBuilder = get("/api/v1/wallets/type");

        this.mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpectAll(
                        status().is2xxSuccessful(),
                        content().contentType(MediaType.APPLICATION_JSON)
                );
    }


}