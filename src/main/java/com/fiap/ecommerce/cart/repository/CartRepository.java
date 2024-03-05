package com.fiap.ecommerce.cart.repository;

import com.fiap.ecommerce.cart.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, String> {
}
