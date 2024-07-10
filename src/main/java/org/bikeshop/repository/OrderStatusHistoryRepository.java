package org.bikeshop.repository;

import java.util.List;
import org.bikeshop.model.OrderStatusHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderStatusHistoryRepository extends JpaRepository<OrderStatusHistory, Long> {

    @Query("SELECT osh FROM OrderStatusHistory osh " +
            "LEFT JOIN FETCH osh.order WHERE osh.order.id=:orderId")
    List<OrderStatusHistory> findAllByOrderId(Long orderId);

}
