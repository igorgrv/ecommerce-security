package com.fiap.ecommerce.cart.controller;

import com.fiap.ecommerce.cart.entity.Cart;
import com.fiap.ecommerce.item.entity.Item;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fiap.ecommerce.cart.service.CartService;

@RestController
@RequestMapping("/cart")
@Tag(name = "Carts", description = "Methods for manipulating Cart's data")
@RequiredArgsConstructor
public class CartController {
    private final CartService service;

    @Operation(summary = "Get a Item by ID", description = "Method to get a Item based on the ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "SUCCESS", content = @Content(schema = @Schema(implementation = Item.class), mediaType = MediaType.APPLICATION_JSON_VALUE)),
    })
    @GetMapping("{id}")
    public ResponseEntity<Cart> getById(@PathVariable("id") String cartId) {
        Cart existingCardOptional = service.findById(cartId);
        return new ResponseEntity<>(existingCardOptional, HttpStatus.OK);
    }
}
