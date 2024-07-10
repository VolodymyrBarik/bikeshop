package org.bikeshop.service.impls;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.bikeshop.dto.response.OrderStatusHistoryResponseDto;
import org.bikeshop.mapper.OrderStatusHistoryMapper;
import org.bikeshop.model.Order;
import org.bikeshop.model.OrderStatusHistory;
import org.bikeshop.model.Status;
import org.bikeshop.repository.OrderStatusHistoryRepository;
import org.bikeshop.service.OrderStatusHistoryService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderStatusHistoryServiceImpl implements OrderStatusHistoryService {
    private final OrderStatusHistoryRepository orderStatusHistoryRepository;
    private final OrderStatusHistoryMapper orderStatusHistoryMapper;

    @Override
    public OrderStatusHistoryResponseDto create(Order order, Status status) {
        OrderStatusHistory orderStatusHistory = new OrderStatusHistory();
        orderStatusHistory.setOrder(order);
        orderStatusHistory.setStatus(status);
        orderStatusHistory.setComment(status.getMessage());
        //orderStatusHistory.setLogin();
        orderStatusHistory.setTimestamp(LocalDateTime.now());
        OrderStatusHistory orderStatusHistoryFromDb =
                orderStatusHistoryRepository.save(orderStatusHistory);
        return orderStatusHistoryMapper.toResponseDto(orderStatusHistoryFromDb);
    }

    @Override
    public List<OrderStatusHistoryResponseDto> getAllByOrder(Long orderId) {
        return orderStatusHistoryRepository.findAllByOrderId(orderId)
                .stream()
                .map(orderStatusHistoryMapper::toResponseDto)
                .sorted(Comparator.comparing(OrderStatusHistoryResponseDto::getTimestamp).reversed())
                .toList();
    }
}
