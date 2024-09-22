package org.bikeshop.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.bikeshop.dto.response.OrderItemResponseDto;
import org.bikeshop.dto.response.OrderStatusHistoryResponseDto;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Setter
@Getter
public class UpdateOrderRequestDto {
    private Long id;
    private Long userId;
    private Long statusId;
    private String currentStatus;
    private Set<OrderItemResponseDto> orderItems;
    private List<OrderStatusHistoryResponseDto> orderStatusHistory;
    private BigDecimal total;
    private String shippingAddress;
    private String additionalComment;
    private boolean isCalculated;
    private Boolean isPaid;
    private Boolean isDeleted;
}
