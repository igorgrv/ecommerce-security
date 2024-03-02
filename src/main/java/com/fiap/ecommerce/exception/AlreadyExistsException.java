package com.fiap.ecommerce.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class AlreadyExistsException extends RuntimeException {

    @Getter
    private final String message;

}
