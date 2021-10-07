package com.rs2.ecommerce.basket;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

public interface BasketAPI {
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    BasketDTO createOrder(@Valid @RequestBody BasketDTO basket);

    @PostMapping("/multiple")
    @ResponseStatus(HttpStatus.CREATED)
    List<BasketDTO> createOrders(@Valid @RequestBody List<BasketDTO> basket);

    @DeleteMapping("/{basketId}")
    void deleteOrder(@PathVariable long basketId);


}
