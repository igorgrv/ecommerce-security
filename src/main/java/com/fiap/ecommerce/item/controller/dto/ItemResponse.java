package com.fiap.ecommerce.item.controller.dto;

import java.math.BigDecimal;

import com.fiap.ecommerce.item.entity.Item;

public record ItemResponse (String name, BigDecimal price) {

    public static ItemResponse fromEntity(Item item) {
        return new ItemResponse(item.getName(), item.getPrice());
    }
}
