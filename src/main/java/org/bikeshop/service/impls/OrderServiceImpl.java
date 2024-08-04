package org.bikeshop.service.impls;

import lombok.RequiredArgsConstructor;
import org.bikeshop.dto.request.OrderRequestDto;
import org.bikeshop.dto.request.UpdateOrderRequestDto;
import org.bikeshop.dto.response.OrderItemResponseDto;
import org.bikeshop.dto.response.OrderResponseDto;
import org.bikeshop.dto.response.OrderStatusHistoryResponseDto;
import org.bikeshop.exception.EntityNotFoundException;
import org.bikeshop.exception.InsufficientProductQuantityException;
import org.bikeshop.mapper.OrderItemMapper;
import org.bikeshop.mapper.OrderMapper;
import org.bikeshop.model.*;
import org.bikeshop.repository.*;
import org.bikeshop.repository.product.ProductRepository;
import org.bikeshop.service.OrderService;
import org.bikeshop.service.OrderStatusHistoryService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final static Long NEW_ORDER_STATUS = 1L;
    private final ShoppingCartRepository shoppingCartRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final CartItemRepository cartItemRepository;
    private final OrderItemMapper orderItemMapper;
    private final OrderMapper orderMapper;
    private final StatusRepository statusRepository;
    private final ProductRepository productRepository;
    private final OrderStatusHistoryService orderStatusHistoryService;
    private final OrderStatusHistoryRepository orderStatusHistoryRepository;
    private final UserRepository userRepository;
    private final ShoppingCartServiceImpl shoppingCartServiceImpl;

    @Override
    public OrderResponseDto create(User user, OrderRequestDto dto) {
        ShoppingCart shoppingCart = shoppingCartRepository.findShoppingCartByUser_Id(user.getId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "You're shopping cart is empty, "
                                + "you have to place good's into the shopping cart"));
        Order orderFromDb = setUpOrder(user, dto, shoppingCart);
        createOrderItems(shoppingCart, orderFromDb);
        clearUserShoppingCart(user);
        return getOrderConfirmation(orderFromDb, user);
    }

    @Override
    public List<OrderResponseDto> findAllByUser(User user, Pageable pageable) {
        List<Order> allOrderByUserId = orderRepository.findAllByUserId(user.getId(), pageable);
        return allOrderByUserId.stream()
                .map(orderMapper::toDto)
                .sorted(Comparator.comparing(OrderResponseDto::getOrderDateTime).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderResponseDto> findAll(Pageable pageable) {
        List<Order> allOrdersFromDb = orderRepository.findAll();
        return allOrdersFromDb.stream()
                .map(orderMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void updateStatus(Long orderId, Long statusId) {
        Status statusFromDb = statusRepository.findById(statusId).orElseThrow(() -> new NoSuchElementException(
                "Can't find status with id " + statusId + " in order " + orderId));
        Order order = orderRepository.findById(orderId).orElseThrow(
                () -> new EntityNotFoundException("Can't find order number # " + orderId));
        order.setCurrentStatus(statusFromDb);
        OrderStatusHistoryResponseDto orderStatusHistoryResponseDto = orderStatusHistoryService.create(order, statusFromDb);
        updateOrderStatusHistoryList(orderId, orderStatusHistoryResponseDto.getId());
        orderRepository.save(order);
    }

    public Boolean checkWhetherTheresEnoughSCProductsInStock(ShoppingCart shoppingCart)
            throws InsufficientProductQuantityException {
        Set<CartItem> cartItems = shoppingCart.getCartItems();
        for (CartItem item : cartItems) {
            Product productFromDb =
                    productRepository.findById(item.getProduct().getId()).orElseThrow(
                            () -> new EntityNotFoundException(
                                    "Cant find product with id " + item.getProduct().getId()));
            if (item.getQuantity() > productFromDb.getQuantity()) {
                throw new InsufficientProductQuantityException(
                        "Unfortunately there's only " + productFromDb.getQuantity() + " " +
                                productFromDb.getTitle() + " left in stock");
            }
        }
        return true;
    }

    @Override
    public void updateOrder(Long orderId, UpdateOrderRequestDto requestDto) {
        Order orderFromDb = orderRepository.findById(orderId).orElseThrow(
                () -> new EntityNotFoundException("Can't find order with id " + orderId));
        if (requestDto.getUserId() != null && !Objects.equals(orderFromDb.getUser().getId(), requestDto.getUserId())) {
            User user = userRepository.findById(requestDto.getUserId()).orElseThrow(
                    () -> new EntityNotFoundException("Can't find user with id " + requestDto.getUserId()));
            orderFromDb.setUser(user);
        }

        if (requestDto.getStatusId() != null && !Objects.equals(
                requestDto.getStatusId(), orderFromDb.getCurrentStatus().getId())) {

            updateStatus(orderId, requestDto.getStatusId());
        }

        if (requestDto.getTotal() != null && !Objects.equals(orderFromDb.getTotal(), requestDto.getTotal())) {
            orderFromDb.setTotal(requestDto.getTotal());
        }

        if (requestDto.getShippingAddress() != null && !Objects.equals(
                orderFromDb.getShippingAddress(), requestDto.getShippingAddress())) {
            orderFromDb.setShippingAddress(requestDto.getShippingAddress());
        }

        if (requestDto.getAdditionalComment() != null) {
            orderFromDb.setAdditionalComment(requestDto.getAdditionalComment());
        }

        orderFromDb.setCalculated(requestDto.isCalculated());

        if (requestDto.getIsPaid() != null) {
            orderFromDb.setPaid(requestDto.getIsPaid());
        }

        if (requestDto.getIsDeleted() != null) {
            orderFromDb.setDeleted(requestDto.getIsDeleted());
        }

        // Оновлення списку OrderItems
        if (requestDto.getOrderItems() != null) {
            // Видалення старих записів
            orderFromDb.getOrderItems().clear();
            for (OrderItemResponseDto orderItemDto : requestDto.getOrderItems()) {
                OrderItem orderItem = new OrderItem();
                // Присвоєння властивостей orderItem на основі orderItemDto
                orderItem.setOrder(orderFromDb);
                orderFromDb.getOrderItems().add(orderItem);
            }
        }
        //update orderStatusHistory
        // Оновлення списку OrderStatusHistoryList
        if (requestDto.getOrderStatusHistory() != null) {
            // Видалення старих записів
            orderFromDb.getOrderStatusHistoryList().clear();
            for (OrderStatusHistoryResponseDto statusHistoryDto : requestDto.getOrderStatusHistory()) {
                OrderStatusHistory statusHistory = new OrderStatusHistory();
                // Присвоєння властивостей statusHistory на основі statusHistoryDto
                statusHistory.setOrder(orderFromDb);
                orderFromDb.getOrderStatusHistoryList().add(statusHistory);
            }
        }

        orderRepository.save(orderFromDb);
    }

    private Order setUpOrder(User user, OrderRequestDto dto, ShoppingCart shoppingCart) {
        Order order = new Order();
        Status statusNEWFromDb = statusRepository.findById(NEW_ORDER_STATUS)
                .orElseThrow(() -> new EntityNotFoundException("Can't find status with id 1"));
        //orderStatusService.updateOrderStatus()
        order.setCurrentStatus(statusNEWFromDb);
        order.setUser(user);
        order.setShippingAddress(dto.getShippingAddress());
        order.setOrderDate(LocalDateTime.now());
        //orderItems add to order

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
        subtractCartItemQuantityFromProductQuantity(cartItem);
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
        responseDto.setOrderDateTime(orderFromDb.getOrderDate());
        Set<OrderItemResponseDto> orderItemsResponseDtoSet = orderFromDb.getOrderItems().stream()
                .map(orderItemMapper::toResponseDto)
                .collect(Collectors.toSet());
        responseDto.setOrderItems(orderItemsResponseDtoSet);
        responseDto.setUserId(orderFromDb.getUser().getId());
        responseDto.setStatusId(orderFromDb.getCurrentStatus().getId());
        responseDto.setTotal(orderFromDb.getTotal());
        return responseDto;
    }

    private void subtractCartItemQuantityFromProductQuantity(CartItem cartItem) {
        Product productFromDb = productRepository.findById(cartItem.getProduct().getId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Can't find product with id " + cartItem.getProduct().getId()));
        int remainingQuantity = productFromDb.getQuantity() - cartItem.getQuantity();
        if (remainingQuantity >= 0) {
            productFromDb.setQuantity(remainingQuantity);
            productRepository.save(productFromDb);
        } else {
            throw new InsufficientProductQuantityException(productFromDb.getTitle() +
                    "is not enough, there's only " + productFromDb.getQuantity() +
                    " left in stock");
        }
    }

    private void updateOrderStatusHistoryList(Long orderId, Long orderStatusHistoryId) {
        Order order = orderRepository.findById(orderId).orElseThrow(
                () -> new EntityNotFoundException("Can't find order with id " + orderId));
        List<OrderStatusHistory> orderStatusHistoryList = order.getOrderStatusHistoryList();

        OrderStatusHistory orderStatusHistory = orderStatusHistoryRepository.findById(orderStatusHistoryId).orElseThrow(() -> new EntityNotFoundException(
                "Can't find orderStatusHistory with id " + orderStatusHistoryId));
        orderStatusHistoryList.add(orderStatusHistory);
        order.setOrderStatusHistoryList(orderStatusHistoryList);
        orderRepository.save(order);
    }

    private void clearUserShoppingCart(User user) {
        ShoppingCart shoppingCart = shoppingCartRepository.findShoppingCartByUser_Id(user.getId())
                .orElseThrow(
                        () -> new EntityNotFoundException(
                                "Can't find user shopping cart with user id " + user.getId()));
        Set<CartItem> cartItems = shoppingCart.getCartItems();
        cartItemRepository.deleteAll(cartItems);
    }
}
