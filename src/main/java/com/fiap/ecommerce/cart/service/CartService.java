package com.fiap.ecommerce.cart.service;

import com.fiap.ecommerce.cart.entity.Cart;
import com.fiap.ecommerce.cart.repository.CartRepository;
import com.fiap.ecommerce.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository repository;

    public Cart findById(String itemId) {
        return repository
            .findById(itemId)
            .orElseThrow(() -> new NotFoundException("Could not find any item given id: " + itemId));
    }
}
