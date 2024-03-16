package com.fiap.ecommerce.cart.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.fiap.ecommerce.item.entity.Item;
import com.fiap.ecommerce.user.entity.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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

    @OneToOne(mappedBy = "cart")
    private User user;

    @ManyToMany(mappedBy = "carts", cascade = CascadeType.PERSIST)
    private List<Item> cartItemList = new ArrayList<>();

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

    public void setUser(User user) {
        this.user = user;
    }

    public Cart(User user) {
        this.setUser(user);
        this.setQuantity(0);
        this.setTotalPrice(BigDecimal.ZERO);
        this.setCartItemList(List.of());
    }

}
