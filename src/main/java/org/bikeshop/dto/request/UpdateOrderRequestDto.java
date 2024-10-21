package org.bikeshop.dto.request;

import java.math.BigDecimal;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.bikeshop.dto.response.OrderStatusHistoryResponseDto;

@Setter
@Getter
public class UpdateOrderRequestDto {
    private Long id;
    private Long userId;
    private Long statusId;
    private String currentStatus;
    private List<OrderItemRequestDto> orderItems;
    private List<OrderStatusHistoryResponseDto> orderStatusHistory;
    private BigDecimal total;
    private String shippingAddress;
    private String additionalComment;
    private boolean isCalculated;
    private Boolean isPaid;
    private Boolean isDeleted;
}
