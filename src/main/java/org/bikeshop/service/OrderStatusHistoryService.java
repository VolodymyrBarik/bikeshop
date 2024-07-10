package org.bikeshop.service;

import java.util.List;
import org.bikeshop.dto.response.OrderStatusHistoryResponseDto;
import org.bikeshop.model.Order;
import org.bikeshop.model.Status;

public interface OrderStatusHistoryService {
    OrderStatusHistoryResponseDto create(Order order, Status status);

    List<OrderStatusHistoryResponseDto> getAllByOrder(Long orderId);
}
