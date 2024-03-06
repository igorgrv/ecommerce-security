package com.fiap.ecommerce.cart.service;

import com.fiap.ecommerce.cart.controller.dto.AddRequest;
import com.fiap.ecommerce.cart.controller.dto.CartResponse;
import com.fiap.ecommerce.cart.entity.Cart;
import com.fiap.ecommerce.user.entity.User;
import com.fiap.ecommerce.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartService {
    private final UserService userService;

    public CartResponse findCartById(String id) {
        User user = userService.findById(id);
        Cart cart = user.getCart();
        return new CartResponse(user.getId(), cart);
    }

    public void addItemToCart(AddRequest addRequest) {
        User user = userService.findById(addRequest.userId());
        Cart cart = user.getCart();
        cart.addItem(addRequest.item());
        userService.save(user);
    }
}
