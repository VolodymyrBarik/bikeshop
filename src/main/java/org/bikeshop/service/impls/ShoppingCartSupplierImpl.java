package org.bikeshop.service.impls;

import lombok.RequiredArgsConstructor;
import org.bikeshop.model.ShoppingCart;
import org.bikeshop.model.User;
import org.bikeshop.repository.ShoppingCartRepository;
import org.bikeshop.service.ShoppingCartSupplier;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShoppingCartSupplierImpl implements ShoppingCartSupplier {
    private final ShoppingCartRepository repository;

    @Override
    public ShoppingCart createShoppingCart(User user) {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUser(user);
        return repository.save(shoppingCart);
    }
}
