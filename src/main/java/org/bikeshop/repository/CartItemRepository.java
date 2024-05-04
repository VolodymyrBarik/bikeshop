package org.bikeshop.repository;

import java.util.Optional;
import org.bikeshop.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    Optional<CartItem> findByShoppingCartIdAndProduct_id(Long bookId, Long shoppingCartId);

    Optional<CartItem> findByIdAndShoppingCartId(Long id, Long shoppingCartId);
}
