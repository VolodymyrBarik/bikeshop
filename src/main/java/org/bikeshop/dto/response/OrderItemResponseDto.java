package org.bikeshop.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemResponseDto {
    private Long id;
    private String title;
    private Long productId;
    private Long price;
    private int quantity;
}
