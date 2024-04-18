package org.bikeshop.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CurrencyResponseDto {
    private Long id;
    private String name;
    private double exchangeRate;
    private boolean isDeleted;
    private boolean isEnabled;
}
