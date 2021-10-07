package com.rs2.ecommerce.basket;

import java.util.List;

public interface BasketService {
    Basket createOrder(Basket basket);

    List<Basket> createOrders(List<Basket> baskets);

    void deleteOrder(long basketId);
}
