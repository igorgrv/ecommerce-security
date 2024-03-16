package com.fiap.ecommerce.item.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.fiap.ecommerce.exception.AlreadyExistsException;
import com.fiap.ecommerce.exception.NotFoundException;
import com.fiap.ecommerce.item.controller.dto.ItemRequest;
import com.fiap.ecommerce.item.controller.dto.ItemResponse;
import com.fiap.ecommerce.item.entity.Item;
import com.fiap.ecommerce.item.repository.ItemRepository;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository repository;

    public List<ItemResponse> findAll() {
        return repository.findAll().stream().map(ItemResponse::fromEntity).collect(Collectors.toList());
    }

    public Item findById(String itemId) {
        return repository
                .findById(itemId)
                .orElseThrow(() -> new NotFoundException("Could not find any item given id: " + itemId));
    }

    public Boolean existByName(String itemName) {
        return repository.existsByName(itemName);
    }

    public void delete(String id) {
        findById(id);
        repository.deleteById(id);
    }

    public Item registerItem(@Valid ItemRequest request) {
        if (Boolean.TRUE.equals(existByName(request.name())))
            throw new AlreadyExistsException("Item already registered");

        return repository.save(new Item(request));
    }

}
