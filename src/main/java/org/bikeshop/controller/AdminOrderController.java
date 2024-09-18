package org.bikeshop.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.bikeshop.dto.response.OrderListDto;
import org.bikeshop.dto.response.OrderResponseDto;
import org.bikeshop.service.OrderService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("admin/orders")
public class AdminOrderController {
    private final OrderService orderService;

    // HOW CAN ADMIN CREATE ORDERS

    @GetMapping("/all")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "Returns all the orders. Newest comes first",
            description = "Returns list of all orders. Newest comes first")
    List<OrderListDto> getAll(Pageable pageable) {
        return orderService.findAll(pageable);
    }

    @GetMapping("/{orderId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "Returns an order by its id",
            description = "Returns an order by its id")
    OrderResponseDto getOrderById(@PathVariable Long orderId) {
        return orderService.findById(orderId);
    }

    //    @PutMapping("/{id}")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    @Operation(summary = "Updates an order by id",
//            description = "Updates an order by id")
//    public void update(@PathVariable Long id,
//                       @RequestBody
//                       @Valid CreateCurrencyRequestDto requestDto) {
//        orderService.updateOrder (id, requestDto);


//    @GetMapping("/{orderId}/items")
//@PreAuthorize("hasRole('ROLE_ADMIN')")
//    @Operation(summary = "Returns all the items belongs to order",
//            description = "Returns list of orderItems that certain order contains")
//    List<OrderItemResponseDto> getAllItemsFromOrder(
//            @PathVariable Long orderId, Authentication authentication) {
//        User user = (User) authentication.getPrincipal();
//        return orderItemService.getOrderItemByOrderId(orderId, user);
//    }

//    @GetMapping("/{orderId}/items/{orderItemId}")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    @Operation(summary = "Returns certain orderItem from the certain order",
//            description = "Returns certain orderItem from the certain order that belongs to user")
//    OrderItemResponseDto getItemFromOrder(
//            @PathVariable Long orderId, @PathVariable Long orderItemId,
//            Authentication authentication) {
//        User user = (User) authentication.getPrincipal();
//        return orderItemService.getItemFromOrder(orderId, user, orderItemId);
//    }

    @PutMapping("/{orderId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @Operation(summary = "Update order status",
            description = "Updates order status by admin")
    void updateStatus(@PathVariable Long orderId,
                      @RequestBody Long statusId) {
        orderService.updateStatus(orderId, statusId);
    }
}
