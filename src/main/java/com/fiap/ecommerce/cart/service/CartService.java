package com.fiap.ecommerce.cart.service;

import org.springframework.stereotype.Service;

import com.fiap.ecommerce.cart.controller.dto.CartRequest;
import com.fiap.ecommerce.cart.controller.dto.CartResponse;
import com.fiap.ecommerce.cart.entity.Cart;
import com.fiap.ecommerce.item.entity.Item;
import com.fiap.ecommerce.item.service.ItemService;
import com.fiap.ecommerce.user.entity.User;
import com.fiap.ecommerce.user.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartService {
    private final UserService userService;
    private final ItemService itemService;

    public CartResponse findCartById(String id) {
        User user = userService.findById(id);
        Cart cart = user.getCart();
        return CartResponse.fromEntity(user.getId(), cart);
    }

    public void addItemToCart(CartRequest addRequest) {
        User user = userService.findById(addRequest.userId());
        Cart cart = user.getCart();
        
        String itemId = addRequest.itemId();
        Item item = itemService.findById(itemId);
        
        cart.addItem(item);
        itemService.addCartToItem(cart, item);
        userService.save(user);
    }

    public void deleteItemFromCart(CartRequest removeRequest) {
        User user = userService.findById(removeRequest.userId());
        Cart cart = user.getCart();

        String itemId = removeRequest.itemId();
        Item item = itemService.findById(itemId);

        cart.removeItem(item);
        userService.save(user);
    }
}
