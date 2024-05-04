package org.bikeshop.service;

import org.bikeshop.dto.request.CartItemRequestDto;
import org.bikeshop.model.CartItem;
import org.bikeshop.model.User;

public interface CartItemService {
    CartItem create(CartItemRequestDto requestDto, User user);
}
