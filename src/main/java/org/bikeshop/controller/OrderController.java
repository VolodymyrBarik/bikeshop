package org.bikeshop.controller;

import lombok.RequiredArgsConstructor;
import org.bikeshop.service.OrderItemService;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController("/orders")
public class OrderController {
    private final OrderService orderService;
    private final OrderItemService orderItemService;
}
