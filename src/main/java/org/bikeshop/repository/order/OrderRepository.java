package org.bikeshop.repository.order;

import java.util.List;
import java.util.Optional;
import org.bikeshop.model.Order;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;

public interface OrderRepository extends JpaRepository<Order, Long>,
        JpaSpecificationExecutor<Order> {
    @EntityGraph(attributePaths = {"orderItems", "orderItems.product", "currentStatus"})
    List<Order> findAllByUserId(Long userId, Pageable pageable);

    @NonNull
    @Query("SELECT o FROM Order o " +
            "LEFT JOIN FETCH o.orderItems oi " +
            "LEFT JOIN FETCH oi.product " +
            "LEFT JOIN FETCH o.user " +
            "WHERE oi.order.id = :id")
    Optional<Order> findById(@NonNull Long id);
}
