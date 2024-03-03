package com.fiap.ecommerce.user.controller.dto;

import com.fiap.ecommerce.user.entity.enums.UserRole;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(title = "UserRequest", description = "Object that represents a user request")
public record UserRequest(
    
    @NotBlank(message = "login is mandatory")
    @Size(min = 2, max = 50, message = "size must be between {min} and {max}")
    @Schema(description = "login to identify the user", example = "ecommerce@fiap.com")
    String login,
    
    @NotBlank(message = "password is mandatory")
    @Size(min = 8, max = 20, message = "size must be between {min} and {max}")
    @Schema(description = "password to authenticate the user", example = "12345678")
    String password,
    
    @Schema(description = "role to authorize the user", example = "ADMIN")
    UserRole role) {
}
