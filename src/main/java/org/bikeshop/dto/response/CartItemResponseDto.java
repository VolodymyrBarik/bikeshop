package org.bikeshop.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemResponseDto {
    private Long id;
    private Long productId;
    private String productTitle;
    private int quantity;
}
