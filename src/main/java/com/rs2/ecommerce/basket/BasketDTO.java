package com.rs2.ecommerce.basket;

import com.rs2.ecommerce.product.Product;
import lombok.Data;

import java.io.Serializable;

@Data
public class BasketDTO implements Serializable {
    private Product product;
}
