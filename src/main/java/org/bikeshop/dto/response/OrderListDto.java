package org.bikeshop.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class OrderListDto {
    private Long id;
    private Long userId;
    private Long statusId;
    private String currentStatus;
    private LocalDateTime orderDateTime;
    private BigDecimal total;
    private Boolean isPaid;
    private boolean isDeleted;
}
