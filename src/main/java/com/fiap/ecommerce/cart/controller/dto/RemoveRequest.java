package com.fiap.ecommerce.cart.controller.dto;

import com.fiap.ecommerce.item.entity.Item;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(title = "RemoveRequest", description = "Object that represents a remove request")
public record RemoveRequest(

    @NotBlank(message = "userId is mandatory")
    @Schema(description = "userId to identify the user", example = "11111")
    String userId,

    @NotBlank(message = "item is mandatory")
    @Schema(description = "item to remove", example = "Item")
    Item item){

}
