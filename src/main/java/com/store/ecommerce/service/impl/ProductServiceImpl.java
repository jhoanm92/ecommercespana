package com.store.ecommerce.service.impl;

import com.store.ecommerce.model.Product;
import com.store.ecommerce.service.feint.ProductFeingClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl{

    @Autowired
    private ProductFeingClient productFeingClient;

    public List<Product> getProducts(Integer id){
        return getListIdSimilarProduct(id)
                .stream()
                .map(x -> productFeingClient.getProductById(x))
                .toList();
    }

    public List<Integer> getListIdSimilarProduct(Integer id){
        return productFeingClient.getSimilarProduct(id)
                .stream()
                .mapToInt(Integer::parseInt)
                .boxed()
                .toList();
    }
}