package org.bikeshop.service.impls;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.bikeshop.dto.request.OrderRequestDto;
import org.bikeshop.dto.request.OrderStatusRequestDto;
import org.bikeshop.dto.response.OrderItemResponseDto;
import org.bikeshop.dto.response.OrderResponseDto;
import org.bikeshop.exception.EntityNotFoundException;
import org.bikeshop.mapper.OrderItemMapper;
import org.bikeshop.mapper.OrderMapper;
import org.bikeshop.model.CartItem;
import org.bikeshop.model.Order;
import org.bikeshop.model.OrderItem;
import org.bikeshop.model.ShoppingCart;
import org.bikeshop.model.Status;
import org.bikeshop.model.User;
import org.bikeshop.repository.CartItemRepository;
import org.bikeshop.repository.OrderItemRepository;
import org.bikeshop.repository.OrderRepository;
import org.bikeshop.repository.ShoppingCartRepository;
import org.bikeshop.repository.StatusRepository;
import org.bikeshop.service.OrderService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final ShoppingCartRepository shoppingCartRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final CartItemRepository cartItemRepository;
    private final OrderItemMapper orderItemMapper;
    private final OrderMapper orderMapper;
    private final StatusRepository statusRepository;

    @Override
    public OrderResponseDto create(User user, OrderRequestDto dto) {
        ShoppingCart shoppingCart = shoppingCartRepository.findShoppingCartByUser_Id(user.getId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "You're shopping cart is empty, "
                                + "you have to place good's into the shopping cart"));
        Order orderFromDb = setUpOrder(user, dto, shoppingCart);
        createOrderItems(shoppingCart, orderFromDb);
        return getOrderConfirmation(orderFromDb, user);
    }

    @Override
    public List<OrderResponseDto> findAll(User user, Pageable pageable) {
        List<Order> allOrderByUserId = orderRepository.findAllByUserId(user.getId(), pageable);
        return allOrderByUserId.stream()
                .map(orderMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void updateStatus(Long orderId, OrderStatusRequestDto statusRequestDto) {
        Status statusFromDb = statusRepository.findById(statusRequestDto.getStatusId()).orElseThrow(
                () -> new NoSuchElementException(
                        "Can't find status with id " + statusRequestDto.getStatusId()));
        Order order = orderRepository.findById(orderId).orElseThrow(
                () -> new EntityNotFoundException("Can't find order number # " + orderId));
        order.setCurrentStatus(statusFromDb);
        orderRepository.save(order);
    }

    @Override
    public void updateOrder(Long orderId, OrderStatusRequestDto statusRequestDto) {

    }

    private Order setUpOrder(User user, OrderRequestDto dto, ShoppingCart shoppingCart) {
        Order order = new Order();
        Status statusNEWFromDb = statusRepository.findById(1L)
                .orElseThrow(() -> new EntityNotFoundException("Can't find status with id 1"));
        order.setCurrentStatus(statusNEWFromDb);
        order.setUser(user);
        order.setShippingAddress(dto.getShippingAddress());
        order.setOrderDate(LocalDateTime.now());
        BigDecimal totalPrice = getTotalPrice(shoppingCart.getCartItems());
        order.setTotal(totalPrice);
        return saveOrderToDb(order);
    }

    private BigDecimal getTotalPrice(Set<CartItem> items) {
        return items.stream()
                .mapToDouble(cartItem -> cartItem.getProduct().getPriceUah().doubleValue()
                        * cartItem.getQuantity())
                .mapToObj(BigDecimal::valueOf)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private Order saveOrderToDb(Order order) {
        return orderRepository.save(order);
    }

    private void createOrderItems(ShoppingCart shoppingCart, Order orderFromDb) {
        Set<CartItem> cartItems = shoppingCart.getCartItems();
        for (CartItem cartItem : cartItems) {
            OrderItem orderItem = parseCartItemIntoOrderItem(orderFromDb, cartItem);
            orderItemRepository.save(orderItem);
            deleteCartItemFromShoppingCart(cartItem);
            cartItemRepository.save(cartItem);
        }
    }

    private OrderItem parseCartItemIntoOrderItem(Order order, CartItem cartItem) {
        OrderItem orderItem = new OrderItem();
        orderItem.setOrder(order);
        orderItem.setProduct(cartItem.getProduct());
        orderItem.setQuantity(cartItem.getQuantity());
        BigDecimal price = new BigDecimal(String.valueOf(cartItem.getProduct().getPriceUah()));
        price = price.multiply(new BigDecimal(cartItem.getQuantity()));
        orderItem.setPrice(price);
        return orderItem;
    }

    private void deleteCartItemFromShoppingCart(CartItem cartItem) {
        cartItemRepository.delete(cartItem);
    }

    private OrderResponseDto getOrderConfirmation(Order orderFromDb, User user) {
        OrderResponseDto responseDto = new OrderResponseDto();
        responseDto.setId(orderFromDb.getId());
        responseDto.setOrderDate(orderFromDb.getOrderDate());
        Set<OrderItemResponseDto> orderItemsResponseDtoSet = orderFromDb.getOrderItems().stream()
                .map(orderItemMapper::toResponseDto)
                .collect(Collectors.toSet());
        responseDto.setOrderItems(orderItemsResponseDtoSet);
        responseDto.setUserId(orderFromDb.getUser().getId());
        responseDto.setStatus(orderFromDb.getCurrentStatus().getName());
        responseDto.setTotal(orderFromDb.getTotal());
        return responseDto;
    }
}
