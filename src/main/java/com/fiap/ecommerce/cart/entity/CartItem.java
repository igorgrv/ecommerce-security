package com.fiap.ecommerce.cart.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Table(name = "cartItems")
@Entity(name = "cartItems")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;
    private BigDecimal price;

}
