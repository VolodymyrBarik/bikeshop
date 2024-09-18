package org.bikeshop.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.bikeshop.dto.request.OrderRequestDto;
import org.bikeshop.dto.response.OrderListDto;
import org.bikeshop.dto.response.OrderResponseDto;
import org.bikeshop.exception.EntityNotFoundException;
import org.bikeshop.model.User;
import org.bikeshop.service.OrderService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/orders")
public class UserOrderController {
    private final OrderService orderService;

    @PostMapping
    @Operation(summary = "Create a new order", description = "Create a new order")
    OrderResponseDto create(@RequestBody OrderRequestDto requestDto,
                            Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return orderService.create(user, requestDto);
    }

    @GetMapping
    @Operation(summary = "Returns all the orders belongs to user. Newest comes first",
            description = "Returns list of orders belongs to user. Newest comes first")
    List<OrderListDto> getUserOrders(Authentication authentication, Pageable pageable) {
        User user = (User) authentication.getPrincipal();
        return orderService.findAllByUser(user, pageable);
    }

    @GetMapping("/{orderId}")
    @Operation(summary = "Returns an order by its id, only if it belongs to that user",
            description = "Returns an order by its id, checks if orderId belongs to userId")
    OrderResponseDto getOrderByIdForUserId(Authentication authentication, @PathVariable Long orderId) {
        User user = (User) authentication.getPrincipal();
        Pageable defaultPageable = PageRequest.of(0, 10, Sort.by("id").ascending());
        return orderService.findAllByUser(user, defaultPageable).stream()
                .filter(order -> order.getId().equals(orderId))
                .findFirst()
                .map(order -> orderService.findById(orderId))
                .orElseThrow(() -> new EntityNotFoundException("You don't have an order with id " + orderId));
    }
}
