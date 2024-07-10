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
import org.bikeshop.dto.request.UpdateOrderRequestDto;
import org.bikeshop.dto.response.OrderItemResponseDto;
import org.bikeshop.dto.response.OrderResponseDto;
import org.bikeshop.exception.EntityNotFoundException;
import org.bikeshop.exception.InsufficientProductQuantityException;
import org.bikeshop.mapper.OrderItemMapper;
import org.bikeshop.mapper.OrderMapper;
import org.bikeshop.model.CartItem;
import org.bikeshop.model.Order;
import org.bikeshop.model.OrderItem;
import org.bikeshop.model.OrderStatusHistory;
import org.bikeshop.model.Product;
import org.bikeshop.model.ShoppingCart;
import org.bikeshop.model.Status;
import org.bikeshop.model.User;
import org.bikeshop.repository.CartItemRepository;
import org.bikeshop.repository.OrderItemRepository;
import org.bikeshop.repository.OrderRepository;
import org.bikeshop.repository.ShoppingCartRepository;
import org.bikeshop.repository.StatusRepository;
import org.bikeshop.repository.product.ProductRepository;
import org.bikeshop.service.OrderService;
import org.bikeshop.service.OrderStatusHistoryService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
    public List<OrderResponseDto> findAllByUser(User user, Pageable pageable) {
        List<Order> allOrderByUserId = orderRepository.findAllByUserId(user.getId(), pageable);
        return allOrderByUserId.stream()
                .map(orderMapper::toDto)
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
    public void updateStatus(Long orderId, OrderStatusRequestDto statusRequestDto) {
        Status statusFromDb = statusRepository.findById(statusRequestDto.getStatusId()).orElseThrow(
                () -> new NoSuchElementException(
                        "Can't find status with id " + statusRequestDto.getStatusId()));
        Order order = orderRepository.findById(orderId).orElseThrow(
                () -> new EntityNotFoundException("Can't find order number # " + orderId));
        order.setCurrentStatus(statusFromDb);

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
        responseDto.setOrderDate(orderFromDb.getOrderDate());
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

    private OrderStatusHistory updateOrderStatusHistory(Long orderId, Long statusId) {
        return
    }
}
