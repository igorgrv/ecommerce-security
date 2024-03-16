package com.fiap.ecommerce.item.controller;

import java.util.List;

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

import com.fiap.ecommerce.item.controller.dto.ItemRequest;
import com.fiap.ecommerce.item.controller.dto.ItemResponse;
import com.fiap.ecommerce.item.entity.Item;
import com.fiap.ecommerce.item.service.ItemService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/items")
@Tag(name = "Items", description = "Methods for manipulating Item's data")
@RequiredArgsConstructor
@ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "BAD REQUEST - Client error", content = @Content(examples = {
                @ExampleObject(summary = "Bad Request", value = "{\"statusCode\":400,\"message\":\"Bad Request\"}")
        }, mediaType = MediaType.APPLICATION_JSON_VALUE)),
        @ApiResponse(responseCode = "404", description = "NOT FOUND - Item Id not Found", content = @Content(examples = {
                @ExampleObject(summary = "Item ID not found", value = "{\"statusCode\":404,\"message\":\"Item ID not found\"}")
        }, mediaType = MediaType.APPLICATION_JSON_VALUE)),
        @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR - Something went wrong", content = @Content(examples = {
                @ExampleObject(summary = "Internal Server Error", value = "{\"statusCode\":500,\"message\":\"Internal Server Error\"}")
        }, mediaType = MediaType.APPLICATION_JSON_VALUE))
})
public class ItemController {

    private final ItemService service;

    @Operation(summary = "Get all the Items", description = "Method for getting all the Items")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SUCCESS - List of all Items", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ItemResponse.class)), mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
    @GetMapping
    public ResponseEntity<List<ItemResponse>> getAll() {
        List<ItemResponse> items = service.findAll();
        if (items.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @Operation(summary = "Get a Item by ID", description = "Method to get a Item based on the ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SUCCESS", content = @Content(schema = @Schema(implementation = Item.class), mediaType = MediaType.APPLICATION_JSON_VALUE)),
    })
    @GetMapping("{id}")
    public ResponseEntity<Item> getById(@PathVariable("id") String itemId) {
        Item existingItemOptional = service.findById(itemId);
        return new ResponseEntity<>(existingItemOptional, HttpStatus.OK);
    }

    @Operation(summary = "Save an item", description = "Method to save an item")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SUCCESS", content = @Content(schema = @Schema(implementation = Item.class), mediaType = MediaType.APPLICATION_JSON_VALUE)),
    })
    @PostMapping
    public ResponseEntity<Item> register(@RequestBody @Valid ItemRequest request) {
        Item item = service.registerItem(request);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @Operation(summary = "Delete a Item", description = "Method to Delete an existing Item")
    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable("id") String id) {
        service.delete(id);
        String message = "Item " + id + " deleted with success";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

}
