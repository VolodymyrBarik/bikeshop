package org.bikeshop.service;

import org.bikeshop.model.WholesaleUser;

@FunctionalInterface
public interface ShoppingCartSupplier {
    void createShoppingCart(WholesaleUser wholesaleUser);
}
