package com.fiap.ecommerce.cart.controller.dto;

import com.fiap.ecommerce.item.entity.Item;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(title = "AddRequest", description = "Object that represents a add request")
public record AddRequest(

    @NotBlank(message = "userId is mandatory")
    @Schema(description = "userId to identify the user", example = "11111")
    String userId,


    @Schema(description = "item to add", example = "Item")
    Item item){

}
