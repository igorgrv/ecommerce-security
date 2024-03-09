package com.fiap.ecommerce.item.entity;

import java.math.BigDecimal;

import com.fiap.ecommerce.cart.entity.Cart;
import com.fiap.ecommerce.item.controller.dto.ItemRequest;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Table(name = "items")
@Entity(name = "items")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart;

    public Item(ItemRequest itemRequest) {
        this.name = itemRequest.name();
        this.price = itemRequest.price();
    }

}
