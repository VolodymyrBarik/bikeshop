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
    private String companyName;
    private String user;
    private String currentStatus;
    private Set<OrderItemResponseDto> orderItems;
    private List<OrderStatusHistoryResponseDto> orderStatusHistory;
    private String shippingAddress;
    private LocalDateTime orderDateTime;
    private String additionalComment;
    private BigDecimal total;
    private Boolean isPaid;
    private Boolean isDeleted;
    private LocalDateTime createdAt;
}
