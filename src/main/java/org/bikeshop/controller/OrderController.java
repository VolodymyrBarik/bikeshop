package org.bikeshop.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.bikeshop.dto.request.OrderRequestDto;
import org.bikeshop.dto.response.OrderListDto;
import org.bikeshop.dto.response.OrderResponseDto;
import org.bikeshop.exception.EntityNotFoundException;
import org.bikeshop.model.User;
import org.bikeshop.service.OrderItemService;
import org.bikeshop.service.OrderService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/orders")
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
    @Operation(summary = "Returns all the orders. Newest comes first",
            description = "Returns list of all orders. Newest comes first")
    List<OrderListDto> getAll(Pageable pageable) {
        return orderService.findAll(pageable);
    }

    @GetMapping
    @Operation(summary = "Returns all the orders belongs to user. Newest comes first",
            description = "Returns list of orders belongs to user. Newest comes first")
    List<OrderListDto> getUserOrders(Authentication authentication, Pageable pageable) {
        User user = (User) authentication.getPrincipal();
        return orderService.findAllByUser(user, pageable);
    }

    @GetMapping("/{orderId}")
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "Returns an order by its id",
            description = "Returns an order by its id")
    OrderResponseDto getOrderById(@PathVariable Long orderId) {
        return orderService.findById(orderId);
    }

    @GetMapping("/{userId}/{orderId}")
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


//    @PutMapping("/{id}")
//    @Operation(summary = "Updates an order by id",
//            description = "Updates an order by id")
//    public void update(@PathVariable Long id,
//                       @RequestBody
//                       @Valid CreateCurrencyRequestDto requestDto) {
//        orderService.updateOrder (id, requestDto);


//    @GetMapping("/{orderId}/items")
//    @Operation(summary = "Returns all the items belongs to order",
//            description = "Returns list of orderItems that certain order contains")
//    List<OrderItemResponseDto> getAllItemsFromOrder(
//            @PathVariable Long orderId, Authentication authentication) {
//        User user = (User) authentication.getPrincipal();
//        return orderItemService.getOrderItemByOrderId(orderId, user);
//    }

//    @GetMapping("/{orderId}/items/{orderItemId}")
//    @Operation(summary = "Returns certain orderItem from the certain order",
//            description = "Returns certain orderItem from the certain order that belongs to user")
//    OrderItemResponseDto getItemFromOrder(
//            @PathVariable Long orderId, @PathVariable Long orderItemId,
//            Authentication authentication) {
//        User user = (User) authentication.getPrincipal();
//        return orderItemService.getItemFromOrder(orderId, user, orderItemId);
//    }

    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{orderId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @Operation(summary = "Update order status",
            description = "Updates order status by admin")
    void updateStatus(@PathVariable Long orderId,
                      @RequestBody Long statusId) {
        orderService.updateStatus(orderId, statusId);
    }
}
