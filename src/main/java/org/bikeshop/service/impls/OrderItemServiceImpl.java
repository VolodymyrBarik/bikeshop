package org.bikeshop.service.impls;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.bikeshop.dto.response.OrderItemResponseDto;
import org.bikeshop.exception.EntityNotFoundException;
import org.bikeshop.mapper.OrderItemMapper;
import org.bikeshop.model.Order;
import org.bikeshop.model.OrderItem;
import org.bikeshop.model.User;
import org.bikeshop.repository.OrderItemRepository;
import org.bikeshop.repository.OrderRepository;
import org.bikeshop.service.OrderItemService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {
    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;
    private final OrderItemMapper orderItemMapper;

    @Override
    public OrderItem save(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }

    @Override
    public List<OrderItemResponseDto> getOrderItemByOrderId(Long orderId, User user) {
        List<Long> userOrdersIds = orderRepository.findAllByUserId(
                        user.getId(), Pageable.unpaged()).stream()
                .map(Order::getId)
                .toList();
        if (userOrdersIds.contains(orderId)) {
            return orderItemRepository.findAllByOrderId(orderId).stream()
                    .map(orderItemMapper::toResponseDto)
                    .toList();
        } else {
            throw new EntityNotFoundException(
                    user.getFirstName() + " " + user.getLastName()
                            + " doesn't have order with number # " + orderId);
        }
    }

    @Override
    public OrderItemResponseDto getItemFromOrder(Long orderId, User user, Long orderItemId) {
        List<Long> orderItemsIds = getOrderItemByOrderId(orderId, user).stream()
                .map(OrderItemResponseDto::getId)
                .toList();
        if (orderItemsIds.contains(orderItemId)) {
            OrderItem orderItem = orderItemRepository
                    .findById(orderItemId).orElseThrow(() -> new EntityNotFoundException(
                            "No such item " + orderItemId + " in this order " + orderId));
            return orderItemMapper.toResponseDto(orderItem);
        }
        throw new EntityNotFoundException("No such item "
                + orderItemId + " in this order " + orderId);
    }
}
