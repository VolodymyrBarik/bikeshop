package org.bikeshop.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.bikeshop.dto.request.OrderRequestDto;
import org.bikeshop.dto.response.OrderItemResponseDto;
import org.bikeshop.dto.response.OrderResponseDto;
import org.bikeshop.model.User;
import org.bikeshop.service.OrderItemService;
import org.bikeshop.service.OrderService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController("/orders")
public class OrderController {
    private final OrderService orderService;
    private final OrderItemService orderItemService;

    @PostMapping
    @Operation(summary = "Create a new order", description = "Create a new order")
    OrderResponseDto create(@RequestBody OrderRequestDto requestDto,
                            Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return orderService.create(user, requestDto);
    }

    @GetMapping("/all")
    @Operation(summary = "Returns all the orders",
            description = "Returns list of all orders")
    List<OrderResponseDto> getAll(Authentication authentication, Pageable pageable) {
        Sort sort = Sort.by(Sort.Direction.DESC, "orderDateTime");
        Pageable sortedPageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
        return orderService.findAll(sortedPageable);
    }

    @GetMapping
    @Operation(summary = "Returns all the orders belongs to user",
            description = "Returns list of orders belongs to user")
    List<OrderResponseDto> getUsersOrders(Authentication authentication, Pageable pageable) {
        User user = (User) authentication.getPrincipal();
        Sort sort = Sort.by(Sort.Direction.DESC, "orderDateTime");
        Pageable sortedPageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
        return orderService.findAllByUser(user, sortedPageable);
    }

    @GetMapping("/{orderId}/items")
    @Operation(summary = "Returns all the items belongs to order",
            description = "Returns list of orderItems that certain order contains")
    List<OrderItemResponseDto> getAllItemsFromOrder(
            @PathVariable Long orderId, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return orderItemService.getOrderItemByOrderId(orderId, user);
    }

    @GetMapping("/{orderId}/items/{orderItemId}")
    @Operation(summary = "Returns certain orderItem from the certain order",
            description = "Returns certain orderItem from the certain order that belongs to user")
    OrderItemResponseDto getItemFromOrder(
            @PathVariable Long orderId, @PathVariable Long orderItemId,
            Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return orderItemService.getItemFromOrder(orderId, user, orderItemId);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{orderId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @Operation(summary = "Update order status",
            description = "Updates order status by admin")
    void updateStatus(@PathVariable Long orderId,
                      @RequestBody Long statusId) {
        orderService.updateStatus(orderId, statusId);
    }
}
