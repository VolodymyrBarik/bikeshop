package org.bikeshop.controller;

import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.bikeshop.dto.OrderSearchParameters;
import org.bikeshop.dto.request.OrderRequestDto;
import org.bikeshop.dto.request.UpdateOrderRequestDto;
import org.bikeshop.dto.response.OrderItemResponseDto;
import org.bikeshop.dto.response.OrderListDto;
import org.bikeshop.dto.response.OrderResponseDto;
import org.bikeshop.model.User;
import org.bikeshop.service.OrderItemService;
import org.bikeshop.service.OrderService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("admin/orders")
public class AdminOrderController {
    private final OrderService orderService;
    private final OrderItemService orderItemService;

    @PreAuthorize("isAuthenticated() and hasRole('ROLE_ADMIN')")
    @PostMapping
    @Operation(summary = "Create a new order", description = "Create a new order")
    OrderResponseDto create(@RequestBody OrderRequestDto requestDto,
                            Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return orderService.create(user, requestDto);
    }

    @GetMapping("/all")
    @PreAuthorize("isAuthenticated() and hasRole('ROLE_ADMIN')")
    @Operation(summary = "Returns all the orders. Newest comes first",
            description = "Returns list of all orders. Newest comes first")
    List<OrderListDto> getAll(Pageable pageable) {
        return orderService.findAll(pageable);
    }

    @PreAuthorize("isAuthenticated() and hasRole('ROLE_ADMIN')")
    @GetMapping("/{orderId}")
    @Operation(summary = "Returns an order by its id",
            description = "Returns an order by its id")
    OrderResponseDto getOrderById(@PathVariable Long orderId) {
        return orderService.findById(orderId);
    }

    @PreAuthorize("isAuthenticated() and hasRole('ROLE_ADMIN')")
    @PutMapping("/{orderId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @Operation(summary = "Update order status", description = "Updates order status by admin")
    void updateStatus(@PathVariable Long orderId, @RequestBody Long statusId) {
        orderService.updateStatus(orderId, statusId);
    }

    @PreAuthorize("isAuthenticated() and hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    @Operation(summary = "Updates an order by id", description = "Updates an order by id")
    public void update(@PathVariable Long id,
                       @RequestBody UpdateOrderRequestDto requestDto) {
        orderService.updateOrder(id, requestDto);
    }

    @GetMapping("/{orderId}/items")
    @PreAuthorize("isAuthenticated() and hasRole('ROLE_ADMIN')")
    @Operation(summary = "Returns all the items belongs to order",
            description = "Returns list of orderItems that certain order contains")
    List<OrderItemResponseDto> getAllItemsFromOrder(
            @PathVariable Long orderId, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return orderItemService.getOrderItemByOrderId(orderId, user);
    }

    @Operation(summary = "Search an order by the order status", description = "Search an order by it current status")
    @GetMapping("/searchByStatus")
    public List<OrderResponseDto> search(OrderSearchParameters searchParameters,
                                         Pageable pageable) {
        return orderService.searchByStatus(searchParameters, pageable);
    }

    //    @GetMapping("/{orderId}/items/{orderItemId}")
    //    @PreAuthorize("hasRole('ROLE_ADMIN')")
    //    @Operation(summary = "Returns certain orderItem from the certain order",
    //            description = "Returns certain orderItem from the certain order
    //            that belongs to user")
    //    OrderItemResponseDto getItemFromOrder(
    //            @PathVariable Long orderId, @PathVariable Long orderItemId,
    //            Authentication authentication) {
    //        User user = (User) authentication.getPrincipal();
    //        return orderItemService.getItemFromOrder(orderId, user, orderItemId);
    //    }

}
