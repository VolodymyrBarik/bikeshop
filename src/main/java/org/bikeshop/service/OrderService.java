package org.bikeshop.service;

import java.util.List;
import org.bikeshop.dto.request.OrderRequestDto;
import org.bikeshop.dto.request.OrderStatusRequestDto;
import org.bikeshop.dto.response.OrderResponseDto;
import org.bikeshop.model.ShoppingCart;
import org.bikeshop.model.User;
import org.springframework.data.domain.Pageable;

public interface OrderService {
    OrderResponseDto create(User user, OrderRequestDto dto);

    List<OrderResponseDto> findAll(User user, Pageable pageable);

    void updateStatus(Long orderId, OrderStatusRequestDto statusRequestDto);

    void updateOrder(Long orderId, OrderStatusRequestDto statusRequestDto);

    Boolean checkWhetherTheresEnoughSCProductsInStock(ShoppingCart shoppingCart);
}
