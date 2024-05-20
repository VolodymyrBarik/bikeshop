package org.bikeshop.service.impls;

import lombok.RequiredArgsConstructor;
import org.bikeshop.dto.response.ShoppingCartResponseDto;
import org.bikeshop.exception.EntityNotFoundException;
import org.bikeshop.mapper.ShoppingCartMapper;
import org.bikeshop.model.CartItem;
import org.bikeshop.model.ShoppingCart;
import org.bikeshop.model.User;
import org.bikeshop.repository.CartItemRepository;
import org.bikeshop.repository.ShoppingCartRepository;
import org.bikeshop.service.ShoppingCartService;
import org.bikeshop.service.ShoppingCartSupplier;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final ShoppingCartRepository shoppingCartRepository;
    private final ShoppingCartSupplier supplier;
    private final ShoppingCartMapper shoppingCartMapper;
    private final CartItemRepository cartItemRepository;

    @Override
    public ShoppingCartResponseDto get(User user) {
        ShoppingCart shoppingCart = findOrCreateNewShoppingCart(user);
        shoppingCart.setUser(user);
        return shoppingCartMapper.toDto(shoppingCart);
    }

    @Override
    public ShoppingCartResponseDto update(User user, Long cartItemId, int quantity) {
        ShoppingCart shoppingCart = findOrCreateNewShoppingCart(user);
        CartItem cartItem = cartItemRepository
                .findByIdAndShoppingCartId(cartItemId, shoppingCart.getId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Cart item not found with id " + cartItemId + " for shopping cart "
                                + shoppingCart.getId()));
        cartItem.setQuantity(quantity);
        cartItemRepository.save(cartItem);
        return shoppingCartMapper.toDto(findOrCreateNewShoppingCart(user));
    }

    @Override
    public void deleteCartItemFromShoppingCart(User user, Long cartItemId) {
        ShoppingCart shoppingCart = findOrCreateNewShoppingCart(user);
        CartItem cartItemToBeDeleted = cartItemRepository
                .findByIdAndShoppingCartId(cartItemId, shoppingCart.getId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Cart item not found with id " + cartItemId + " for shopping cart "
                                + shoppingCart.getId()));
        cartItemRepository.delete(cartItemToBeDeleted);
    }

    private ShoppingCart findOrCreateNewShoppingCart(User user) {
        return shoppingCartRepository.findShoppingCartByUser_Id(user.getId())
                .orElseGet(() -> supplier.createShoppingCart(user));
    }
}
