package com.rs2.ecommerce.exceptions;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserAlreadyExistsException extends RuntimeException {
    private final String message;
    @Override
    public String getMessage() {
        return message;
    }
}
