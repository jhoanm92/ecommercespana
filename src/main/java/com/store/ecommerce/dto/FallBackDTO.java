package com.store.ecommerce.dto;

import lombok.Data;

@Data
public class FallBackDTO {

    public FallBackDTO(String message) {
        this.message = message;
    }

    private String message;
}
