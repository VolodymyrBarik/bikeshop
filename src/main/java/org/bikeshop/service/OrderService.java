package org.bikeshop.service;

import java.util.List;
import org.bikeshop.dto.request.OrderRequestDto;
import org.bikeshop.dto.request.OrderStatusRequestDto;
import org.bikeshop.dto.request.UpdateOrderRequestDto;
import org.bikeshop.dto.response.OrderListDto;
import org.bikeshop.dto.response.OrderResponseDto;
import org.bikeshop.model.ShoppingCart;
import org.bikeshop.model.User;
import org.springframework.data.domain.Pageable;

public interface OrderService {
    OrderResponseDto create(User user, OrderRequestDto dto);

    List<OrderListDto> findAllByUser(User user, Pageable pageable);

    List<OrderListDto> findAll(Pageable pageable);

    void updateStatus(Long orderId, Long statusId);

    void updateOrder(Long orderId, UpdateOrderRequestDto statusRequestDto);

    Boolean checkWhetherTheresEnoughSCProductsInStock(ShoppingCart shoppingCart);
}
