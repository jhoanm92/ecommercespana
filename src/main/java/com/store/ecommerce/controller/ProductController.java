package com.store.ecommerce.controller;

import com.store.ecommerce.dto.FallBackDTO;
import com.store.ecommerce.model.Product;
import com.store.ecommerce.service.impl.ProductServiceImpl;
import feign.FeignException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private static final Logger log = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductServiceImpl service;

    @CircuitBreaker(name = "productCB", fallbackMethod = "fallBackGetProduct")
    @GetMapping("/{productId}/similar")
    public ResponseEntity<List<Product>> getById(@PathVariable("productId") Integer id) {
        log.info("REST - productId : {}", id);
        return ResponseEntity.ok(service.getProducts(id));
    }

    public ResponseEntity<List<Product>> fallBackGetProduct(@PathVariable("productId") Integer id, FeignException exception) {
        log.info("REST - fallBackGetProduct productId : {}", id);
        return new ResponseEntity(new FallBackDTO("Error consulting the products"), HttpStatus.NOT_FOUND);
    }
}
