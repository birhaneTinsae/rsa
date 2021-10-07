package com.rs2.ecommerce.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public interface ProductService {
    Product createProduct(Product product);

    Product getProduct(long productId);

    void deleteProduct(long productId);

    Product updateProduct(long productId, Product product);

    Page<Product> search(Specification<Product> spec, Pageable pageable);

    public Page<Product> search(String name, ProductType type, Pageable pageable);

    Page<Product> getProducts(Pageable pageable);
}
