package com.store.ecommerce.model;

import lombok.Data;

@Data
public class Product {

    private Integer id;
    private String name;
    private Double price;
    private Boolean availability;
}
