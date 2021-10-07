package com.rs2.ecommerce.exceptions;

public class PasswordMisMatchException extends RuntimeException {
   private String message;

    public PasswordMisMatchException(String message) {
        super(message);

    }
}
