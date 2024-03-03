package com.fiap.ecommerce.item.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fiap.ecommerce.exception.NotFoundException;
import com.fiap.ecommerce.item.entity.Item;
import com.fiap.ecommerce.item.repository.ItemRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository repository;

    public List<Item> findAll() {
        return repository.findAll();
    }

    public Item findById(String itemId) {
        return repository
                .findById(itemId)
                .orElseThrow(() -> new NotFoundException("Could not find any item given id: " + itemId));
    }

    public void delete(String id) {
        findById(id);
        repository.deleteById(id);
    }

}
