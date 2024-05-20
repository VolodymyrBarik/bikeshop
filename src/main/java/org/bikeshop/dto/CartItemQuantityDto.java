package org.bikeshop.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemQuantityDto {
    @NotEmpty
    @Min(1)
    private int quantity;
}
