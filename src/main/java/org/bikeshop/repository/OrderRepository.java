package org.bikeshop.repository;

import java.util.List;
import org.bikeshop.model.Order;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @EntityGraph(attributePaths = {"orderItems", "orderItems.product"})
    List<Order> findAllByUserId(Long userId, Pageable pageable);
}
