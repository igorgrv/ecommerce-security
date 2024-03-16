package com.fiap.ecommerce.cart.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(title = "AddRequest", description = "Object that represents a add request")
public record CartRequest(

    @NotBlank(message = "userId is mandatory")
    @Schema(description = "userId to identify the user", example = "11c9ffff56-4d2c-4b87-8373-a6831d312946111")
    String userId,


    @Schema(description = "item to add", example = "6830f089-848b-45bb-9d45-db43a10ba0d6")
    String itemId){

}
