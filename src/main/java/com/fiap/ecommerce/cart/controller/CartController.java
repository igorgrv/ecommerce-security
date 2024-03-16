package com.fiap.ecommerce.cart.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fiap.ecommerce.cart.controller.dto.CartRequest;
import com.fiap.ecommerce.cart.controller.dto.CartResponse;
import com.fiap.ecommerce.cart.service.CartService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/carts")
@Tag(name = "Carts", description = "Methods for manipulating Cart's data")
@RequiredArgsConstructor
public class CartController {
    private final CartService service;

    @Operation(summary = "Get a cart by userID", description = "Method to get a Cart based on the userID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "SUCCESS", content = @Content(schema = @Schema(implementation = CartResponse.class), mediaType = MediaType.APPLICATION_JSON_VALUE)),
    })
    @GetMapping("/user/{id}")
    public ResponseEntity<CartResponse> getById(@PathVariable("id") String id) {
        CartResponse existingCardOptional = service.findCartById(id);
        return new ResponseEntity<>(existingCardOptional, HttpStatus.OK);
    }

    @Operation(summary = "Add item", description = "Method to add a item to the cart")
    @PostMapping("/add")
    public ResponseEntity<String> register(@RequestBody @Valid CartRequest addRequest) {
        service.addItemToCart(addRequest);
        return new ResponseEntity<>("Item added into cart with success", HttpStatus.CREATED);
    }

    @Operation(summary = "Remove a item", description = "Method to remove a item from the cart")
    @DeleteMapping("/remove")
    public ResponseEntity<String> delete(@RequestBody @Valid CartRequest removeRequest) {
        service.deleteItemFromCart(removeRequest);
        return new ResponseEntity<>("Item removed from cart with success", HttpStatus.OK);
    }
}
