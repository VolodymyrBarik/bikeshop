package org.bikeshop.service;

import java.util.List;
import org.bikeshop.dto.response.OrderItemResponseDto;
import org.bikeshop.model.OrderItem;
import org.bikeshop.model.User;

public interface OrderItemService {
    OrderItem save(OrderItem orderItem);

    List<OrderItemResponseDto> getOrderItemByOrderId(Long orderId, User user);

    OrderItemResponseDto getItemFromOrder(Long orderId, User user, Long orderItemId);
}
