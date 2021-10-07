package com.rs2.ecommerce.product;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProductType {
    BOOK('B'), MUSIC('M'), GAME('G');
    private final char type;
}
