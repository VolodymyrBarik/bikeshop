package org.bikeshop.service;

import java.util.List;
import org.bikeshop.dto.request.CreateCurrencyRequestDto;
import org.bikeshop.dto.response.CurrencyResponseDto;

public interface CurrencyService {
    CurrencyResponseDto save(CreateCurrencyRequestDto requestDto);

    CurrencyResponseDto findById(Long id);

    List<CurrencyResponseDto> findAll();

    List<CurrencyResponseDto> findAllEnabledNonDeleted();

    void update(Long id, CreateCurrencyRequestDto requestDto);

    void delete(Long id);

    void undelete(Long id);

    void enable(Long id);

    void disable(Long id);
}
