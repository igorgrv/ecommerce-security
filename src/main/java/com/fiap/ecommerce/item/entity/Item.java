package com.fiap.ecommerce.item.entity;

import java.math.BigDecimal;

import com.fiap.ecommerce.item.controller.dto.ItemRequest;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

    public Item(ItemRequest itemRequest) {
        this.name = itemRequest.name();
        this.price = itemRequest.price();
    }

}
