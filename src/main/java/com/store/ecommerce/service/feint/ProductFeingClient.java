package com.store.ecommerce.service.feint;

import com.store.ecommerce.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "api", url = "http://localhost:3001/product")
public interface ProductFeingClient {

    @GetMapping("{/productId}")
    Product getProductById(@PathVariable("productId") Integer id);

    @GetMapping("{/productId}/similarids")
    List<String> getSimilarProduct(@PathVariable("productId") Integer id);
}
