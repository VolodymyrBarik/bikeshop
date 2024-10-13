package org.bikeshop.service.impls;

import java.time.LocalDateTime;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.bikeshop.dto.request.CartItemRequestDto;
import org.bikeshop.dto.response.ShoppingCartResponseDto;
import org.bikeshop.exception.EntityNotFoundException;
import org.bikeshop.model.CartItem;
import org.bikeshop.model.Product;
import org.bikeshop.model.ShoppingCart;
import org.bikeshop.model.User;
import org.bikeshop.repository.CartItemRepository;
import org.bikeshop.repository.product.ProductRepository;
import org.bikeshop.service.CartItemService;
import org.bikeshop.service.ShoppingCartService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartItemServiceImpl implements CartItemService {
    private final CartItemRepository cartItemRepository;
    private final ShoppingCartService shoppingCartService;
    private final ProductRepository productRepository;

    @Override
    public CartItem create(CartItemRequestDto requestDto, User user) {
        CartItem existingCartItem = searchIfCartItemExistOrElseCreate(requestDto, user);
        if (existingCartItem.getId() != null) {
            return existingCartItem;
        }
        Product productFromDb = productRepository.findById(requestDto.getProductId()).orElseThrow(
                () -> new EntityNotFoundException("Can't find product with id "
                        + requestDto.getProductId()));
        CartItem cartItem = searchIfCartItemExistOrElseCreate(requestDto, user);
        cartItem.setProduct(productFromDb);
        cartItem.setAddedAt(LocalDateTime.now());
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setId(user.getId());
        cartItem.setShoppingCart(shoppingCart);
        cartItem.setQuantity(requestDto.getQuantity());
        return cartItemRepository.save(cartItem);
    }

    private CartItem searchIfCartItemExistOrElseCreate(CartItemRequestDto requestDto, User user) {
        ShoppingCartResponseDto shoppingCartResponseDto =
                shoppingCartService.get(user);
        Optional<CartItem> byShoppingCartIdAndProductId =
                cartItemRepository.findByShoppingCartIdAndProduct_id(
                        shoppingCartResponseDto.getId(), requestDto.getProductId());
        if (byShoppingCartIdAndProductId.isPresent()) {
            CartItem cartItem = byShoppingCartIdAndProductId.get();
            return updateQuantityOfItem(cartItem, requestDto);
        }
        return new CartItem();
    }

    private CartItem updateQuantityOfItem(CartItem cartItem, CartItemRequestDto requestDto) {
        cartItem.setQuantity(cartItem.getQuantity() + requestDto.getQuantity());
        return cartItemRepository.save(cartItem);
    }
}
