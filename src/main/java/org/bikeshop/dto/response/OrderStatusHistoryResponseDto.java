package org.bikeshop.dto.response;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderStatusHistoryResponseDto {
    private Long id;
    private String statusName;
    private LocalDateTime timestamp;
    private String login;
    private String comment;
    private Long orderId;
}
