package org.bikeshop.service.impls;

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
        if (searchIfCartItemExist(requestDto, user).getId() != null) {
            return searchIfCartItemExist(requestDto, user);
        }
        Product productFromDb = productRepository.findById(requestDto.getProductId()).orElseThrow(
                () -> new EntityNotFoundException("Can't find product with id "
                        + requestDto.getProductId()));
        CartItem cartItem = searchIfCartItemExist(requestDto, user);
        cartItem.setProduct(productFromDb);
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setId(user.getId());
        cartItem.setShoppingCart(shoppingCart);
        cartItem.setQuantity(requestDto.getQuantity());
        return cartItemRepository.save(cartItem);
    }

    private CartItem searchIfCartItemExist(CartItemRequestDto requestDto, User user) {
        ShoppingCartResponseDto shoppingCartResponseDto =
                shoppingCartService.get(user);
        Optional<CartItem> byShoppingCartIdAndProductId =
                cartItemRepository.findByShoppingCartIdAndProduct_id(
                        shoppingCartResponseDto.getId(), requestDto.getProductId());
        if (byShoppingCartIdAndProductId.isPresent()) {
            CartItem cartItem = byShoppingCartIdAndProductId.get();
            cartItem.setQuantity(requestDto.getQuantity());
            return cartItemRepository.save(cartItem);
        }
        return new CartItem();
    }
}
