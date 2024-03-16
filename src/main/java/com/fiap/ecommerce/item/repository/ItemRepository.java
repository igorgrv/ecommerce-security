package com.fiap.ecommerce.item.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fiap.ecommerce.item.entity.Item;

public interface ItemRepository extends JpaRepository<Item, String> {

  Boolean existsByName(String itemId);
}
