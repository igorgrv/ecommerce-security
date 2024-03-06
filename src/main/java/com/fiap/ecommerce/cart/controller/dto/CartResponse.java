package com.fiap.ecommerce.cart.controller.dto;

import com.fiap.ecommerce.cart.entity.Cart;

public record CartResponse(String userId, Cart cart) {
}
