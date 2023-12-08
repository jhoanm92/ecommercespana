package com.store.ecommerce.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void endPoint_get_product_return_OK() throws Exception {
        String productId = "1";
        mockMvc.perform(MockMvcRequestBuilders.get("/product/{productId}/similar", productId))
                .andExpect(status().isOk());
    }

    @Test
    void endPoint_get_product_return_data() throws Exception {
        String productId = "1";
        mockMvc.perform(MockMvcRequestBuilders.get("/product/{productId}/similar", productId))
                .andExpect(jsonPath("$[0].id").value(2))
                .andExpect(jsonPath("$[1].id").value(3))
                .andExpect(jsonPath("$[2].id").value(4));
    }

    @Test
    void endPoint_get_product_error_404_whit_return_NotFound() throws Exception {
        String productId = "4";
        mockMvc.perform(MockMvcRequestBuilders.get("/product/{productId}/similar", productId))
                .andExpect(status().isNotFound());
    }

    @Test
    void endPoint_get_product_error_500_whit_return_NotFound() throws Exception {
        String productId = "5";
        mockMvc.perform(MockMvcRequestBuilders.get("/product/{productId}/similar", productId))
                .andExpect(status().isNotFound());
    }
}