package com.rs2.ecommerce.product;

import lombok.Data;

import java.io.Serializable;

@Data
public class ProductDTO implements Serializable {
    private Long id;
    private ProductType type;
    private String name;
    private String description;
}
