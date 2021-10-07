package com.rs2.ecommerce.product;

import com.rs2.ecommerce.exceptions.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import static com.rs2.ecommerce.utils.Util.getNullPropertyNames;

@Service
@RequiredArgsConstructor
public class ProductServiceImp implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product getProduct(long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException(Product.class, "Product Id", String.valueOf(productId)));
    }

    @Override
    public void deleteProduct(long productId) {
        productRepository.deleteById(productId);
    }

    @Override
    public Product updateProduct(long productId, Product product) {
        var p = getProduct(productId);
        BeanUtils.copyProperties(product, p, getNullPropertyNames(product));
        return productRepository.save(p);
    }

    @Override
    public Page<Product> search(Specification<Product> spec, Pageable pageable) {
        return productRepository.findAll(spec,pageable);
    }

    @Override
    public Page<Product> getProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }
}
