package org.bikeshop.repository;

import java.util.Optional;
import org.bikeshop.model.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    @Query("SELECT sc FROM ShoppingCart sc "
            + "LEFT JOIN FETCH sc.cartItems ci "
            + "LEFT JOIN FETCH ci.product WHERE sc.user.id=:id")
    Optional<ShoppingCart> findShoppingCartByUser_Id(Long id);
}
