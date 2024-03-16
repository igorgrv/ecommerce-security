package com.fiap.ecommerce.item.entity;

import java.math.BigDecimal;
import java.util.List;

import com.fiap.ecommerce.cart.entity.Cart;
import com.fiap.ecommerce.item.controller.dto.ItemRequest;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
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

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinTable(
            name = "items_carts",
            joinColumns = @JoinColumn(name = "item_id"),
            inverseJoinColumns = @JoinColumn(name = "cart_id")
    )
    private List<Cart> carts;

    public Item(ItemRequest itemRequest) {
        this.name = itemRequest.name();
        this.price = itemRequest.price();
    }

    public void addCart(Cart cart) {
        this.carts.add(cart);
    }

}
