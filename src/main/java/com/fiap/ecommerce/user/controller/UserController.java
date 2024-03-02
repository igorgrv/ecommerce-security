package com.fiap.ecommerce.user.controller;

import java.util.ArrayList;
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

import com.fiap.ecommerce.user.controller.dto.UserAuthRequest;
import com.fiap.ecommerce.user.controller.dto.UserRequest;
import com.fiap.ecommerce.user.controller.dto.UserResponse;
import com.fiap.ecommerce.user.entity.User;
import com.fiap.ecommerce.user.service.UserService;

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
@RequestMapping("/users")
@Tag(name = "Users", description = "Methods for manipulating User's data")
@RequiredArgsConstructor
@ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "BAD REQUEST - Client error", content = @Content(examples = {
                @ExampleObject(summary = "Bad Request", value = "{\"statusCode\":400,\"message\":\"Bad Request\"}")
        }, mediaType = MediaType.APPLICATION_JSON_VALUE)),
        @ApiResponse(responseCode = "404", description = "NOT FOUND - User Id not Found", content = @Content(examples = {
                @ExampleObject(summary = "User ID not found", value = "{\"statusCode\":404,\"message\":\"User ID not found\"}")
        }, mediaType = MediaType.APPLICATION_JSON_VALUE)),
        @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR - Something went wrong", content = @Content(examples = {
                @ExampleObject(summary = "Internal Server Error", value = "{\"statusCode\":500,\"message\":\"Internal Server Error\"}")
        }, mediaType = MediaType.APPLICATION_JSON_VALUE))
})
public class UserController {

    private final UserService service;

    @Operation(summary = "Get all the Users", description = "Method for getting all the Users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SUCCESS - List of all Users", content = @Content(array = @ArraySchema(schema = @Schema(implementation = User.class)), mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
    @GetMapping
    public ResponseEntity<List<User>> getAll() {
        List<User> items = new ArrayList<>();
        service.findAll().forEach(items::add);
        if (items.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @Operation(summary = "Get a User by ID", description = "Method to get a User based on the ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SUCCESS", content = @Content(schema = @Schema(implementation = User.class), mediaType = MediaType.APPLICATION_JSON_VALUE)),
    })
    @GetMapping("{id}")
    public ResponseEntity<User> getById(@PathVariable("id") String userId) {
        User existingItemOptional = service.findById(userId);
        return new ResponseEntity<>(existingItemOptional, HttpStatus.OK);
    }

    @Operation(summary = "Login", description = "Method to log in a User")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SUCCESS", content = @Content(schema = @Schema(implementation = UserResponse.class), mediaType = MediaType.APPLICATION_JSON_VALUE)),
    })
    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(@RequestBody @Valid UserAuthRequest data) {
        UserResponse userResponse = service.login(data);

        return ResponseEntity.ok(userResponse);
    }

    @Operation(summary = "Register", description = "Method to register a new User")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SUCCESS", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
    })
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Valid UserRequest userRequest) {
        service.registerUser(userRequest);

        return ResponseEntity.ok("User registered with success");
    }

    // @Operation(summary = "Update a User", description = "Method to update an
    // existing User")
    // @ApiResponses(value = {
    // @ApiResponse(responseCode = "200", description = "SUCCESS - User successfully
    // updated", content = @Content(schema = @Schema(implementation = User.class),
    // mediaType = MediaType.APPLICATION_JSON_VALUE)),
    // })
    // @PutMapping("{id}")
    // public ResponseEntity<User> update(@PathVariable("id") String id, @Valid
    // @RequestBody UserDto user) {
    // User updatedUser = service.update(id, user);
    // return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    // }

    @Operation(summary = "Delete a User", description = "Method to Delete an existing User")
    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable("id") String id) {
        service.delete(id);
        String message = "User " + id + " deleted with success";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

}
