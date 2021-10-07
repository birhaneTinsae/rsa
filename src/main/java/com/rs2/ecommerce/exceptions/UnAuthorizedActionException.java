package com.rs2.ecommerce.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UnAuthorizedActionException extends  RuntimeException {
    private final String message;
}
