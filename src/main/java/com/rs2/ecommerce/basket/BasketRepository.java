package com.rs2.ecommerce.basket;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BasketRepository extends PagingAndSortingRepository<Basket, Long> {
}