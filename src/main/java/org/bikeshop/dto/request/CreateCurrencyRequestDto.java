package org.bikeshop.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCurrencyRequestDto {
    @NotEmpty
    private String name;
    @NotEmpty
    private double exchangeRate;
}
