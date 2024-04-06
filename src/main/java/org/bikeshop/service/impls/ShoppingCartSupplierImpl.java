package org.bikeshop.service.impls;

import lombok.RequiredArgsConstructor;
import org.bikeshop.model.ShoppingCart;
import org.bikeshop.model.WholesaleUser;
import org.bikeshop.repository.ShoppingCartRepository;
import org.bikeshop.service.ShoppingCartSupplier;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShoppingCartSupplierImpl implements ShoppingCartSupplier {
    private final ShoppingCartRepository repository;

    @Override
    public void createShoppingCart(WholesaleUser wholesaleUser) {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUser(wholesaleUser);
        repository.save(shoppingCart);
    }
}
