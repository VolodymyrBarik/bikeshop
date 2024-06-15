package org.bikeshop.dto.request;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import org.bikeshop.dto.response.OrderItemResponseDto;
import org.bikeshop.dto.response.OrderStatusHistoryResponseDto;

@Setter
@Getter
public class UpdateOrderRequestDto {
    private Long id;
    private Long userId;
    private Long statusId;
    private String currentStatus;
    private Set<OrderItemResponseDto> orderItems;
    private List<OrderStatusHistoryResponseDto> orderStatusHistory;
    private LocalDateTime orderDate;
    private BigDecimal total;
    private Boolean isPaid;
    private Boolean isDeleted;
}
