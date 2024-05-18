package org.bikeshop.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemRequestDto {
    @NotNull
    private Long productId;
    @NotNull
    @Min(0)
    private int quantity;
}
