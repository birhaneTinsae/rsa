package com.rs2.ecommerce.basket;

import com.rs2.ecommerce.security.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.rs2.ecommerce.utils.Util.getUsername;

@Service
@RequiredArgsConstructor
public class BasketServiceImp implements BasketService {
    private final BasketRepository basketRepository;
    private final UserService userService;

    @Override
    public Basket createOrder(Basket basket) {
        var user = userService.getUserByUsername(getUsername());
        basket.setUser(user);
        return basketRepository.save(basket);
    }

    @Override
    public List<Basket> createOrders(List<Basket> baskets) {
        return baskets
                .stream()
                .map(this::createOrder)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteOrder(long basketId) {
        basketRepository.deleteById(basketId);
    }
}
