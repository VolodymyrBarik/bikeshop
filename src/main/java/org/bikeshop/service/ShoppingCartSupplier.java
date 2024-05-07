package org.bikeshop.service;

import org.bikeshop.model.ShoppingCart;
import org.bikeshop.model.User;

@FunctionalInterface
public interface ShoppingCartSupplier {
    ShoppingCart createShoppingCart(User user);
}
