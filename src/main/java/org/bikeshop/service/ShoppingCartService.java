package org.bikeshop.service;

import org.bikeshop.dto.response.ShoppingCartResponseDto;
import org.bikeshop.model.User;

public interface ShoppingCartService {
    ShoppingCartResponseDto get(User user);

    ShoppingCartResponseDto update(User user, Long cartItemId, int quantity);

    void deleteCartItemFromShoppingCart(User user, Long cartItemId);
}
