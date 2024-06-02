package org.bikeshop.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderResponseDto {
    private Long id;
    private Long userId;
    private Long statusId;
    private String currentStatus;
    private Set<OrderItemResponseDto> orderItems;
    private List<OrderStatusHistoryResponseDto> orderStatusHistory;
    private LocalDateTime orderDate;
    private BigDecimal total;
    private String status;
    private Boolean isPaid;
}
