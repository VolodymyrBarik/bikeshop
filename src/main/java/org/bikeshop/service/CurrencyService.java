package org.bikeshop.service;

import org.bikeshop.dto.request.CreateCurrencyRequestDto;
import org.bikeshop.dto.response.CurrencyResponseDto;

public interface CurrencyService {
    CurrencyResponseDto save(CreateCurrencyRequestDto requestDto);
}
