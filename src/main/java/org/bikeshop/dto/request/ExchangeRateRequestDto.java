package org.bikeshop.dto.request;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExchangeRateRequestDto {
    @NotNull
    private BigDecimal exchangeRate;
}
