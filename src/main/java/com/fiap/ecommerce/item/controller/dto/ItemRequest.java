package com.fiap.ecommerce.item.controller.dto;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Schema(title = "ItemRequest", description = "Object that represents a item request")
public record ItemRequest(

    @NotBlank(message = "name is mandatory")
    @Size(min = 4, max = 50, message = "size must be between {min} and {max}")
    @Schema(description = "name to identify the item", example = "XBOX")
    String name,
    
    @NotNull(message = "price is mandatory")
    @Schema(description = "price to authenticate the item", example = "1000")
    BigDecimal price) {
}
