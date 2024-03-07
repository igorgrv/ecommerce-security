package com.fiap.ecommerce.cart.entity;

import com.fiap.ecommerce.item.entity.Item;
import com.fiap.ecommerce.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Table(name = "carts")
@Entity(name = "carts")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @OneToOne
    private User userId;

    @OneToMany
    private List<Item> cartItemList;

    private Integer quantity;

    private BigDecimal totalPrice;

    public void addItem(Item item) {
        cartItemList.add(item);
        totalPrice = totalPrice.add(item.getPrice());
        quantity++;
    }

    public void removeItem(Item item) {
        cartItemList.remove(item);
        totalPrice = totalPrice.subtract(item.getPrice());
        quantity--;
    }

}
