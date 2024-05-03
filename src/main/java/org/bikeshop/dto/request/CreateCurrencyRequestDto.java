package org.bikeshop.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCurrencyRequestDto {
    @NotEmpty
    private String name;
    @NotNull
    private BigDecimal exchangeRate;
}
