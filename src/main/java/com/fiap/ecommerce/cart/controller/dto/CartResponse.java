package com.fiap.ecommerce.cart.controller.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import com.fiap.ecommerce.cart.entity.Cart;
import com.fiap.ecommerce.item.controller.dto.ItemResponse;
import com.fiap.ecommerce.item.entity.Item;

public record CartResponse(String userId, String cartId, List<ItemResponse> cartItems, Integer quantity,
        BigDecimal totalprice) {

    public static CartResponse fromEntity(String userId, Cart cart) {
        List<Item> items = cart.getCartItemList();
        List<ItemResponse> itemResponses = items.stream().map(ItemResponse::fromEntity)
                .collect(Collectors.toList());
        return new CartResponse(userId, cart.getId(), itemResponses, cart.getQuantity(), cart.getTotalPrice());
    }
}
