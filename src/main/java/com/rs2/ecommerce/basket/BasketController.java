package com.rs2.ecommerce.basket;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.rs2.ecommerce.utils.Util.dtoMapper;
import static com.rs2.ecommerce.utils.Util.mapList;

@RestController
@RequestMapping("baskets")
@RequiredArgsConstructor
public class BasketController implements BasketAPI {
    private final BasketService basketService;
    private final ModelMapper modelMapper;

    @Override
    public BasketDTO createOrder(BasketDTO basket) {
        return dtoMapper(basketService.createOrder(dtoMapper(basket, Basket.class, modelMapper)), BasketDTO.class, modelMapper);
    }

    @Override
    public List<BasketDTO> createOrders(List<BasketDTO> baskets) {
        return mapList(basketService.createOrders(mapList(baskets, Basket.class, modelMapper)), BasketDTO.class, modelMapper);
    }

    @Override
    public void deleteOrder(long basketId) {
        basketService.deleteOrder(basketId);
    }
}
