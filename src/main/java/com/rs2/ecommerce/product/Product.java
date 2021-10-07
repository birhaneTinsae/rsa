package com.rs2.ecommerce.product;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "products")
@Entity
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Convert(converter = ProductTypeConverter.class)
    private ProductType type;
    @Column(nullable = false)
    private String name;
    @Column(name = "description")
    private String description;

}