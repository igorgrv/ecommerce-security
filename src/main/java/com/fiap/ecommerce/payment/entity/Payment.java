package com.fiap.ecommerce.payment.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record Payment(
    
    @NotBlank(message = "userId is mandatory")
    @Schema(description = "User Id", example = "UUID")
    String userId,

    @NotNull(message =  "payment type is mandatory")
    @Schema(description = "Payment type (DEBIT, CREDIT, PIX)", example = "DEBIT")
    PaymentType paymentType) {}
