package org.bikeshop.service.impls;

import lombok.RequiredArgsConstructor;
import org.bikeshop.dto.request.CreateCurrencyRequestDto;
import org.bikeshop.dto.response.CurrencyResponseDto;
import org.bikeshop.mapper.CurrencyMapper;
import org.bikeshop.model.Currency;
import org.bikeshop.repository.CurrencyRepository;
import org.bikeshop.service.CurrencyService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CurrencyServiceImpl implements CurrencyService {
    private final CurrencyRepository currencyRepository;
    private final CurrencyMapper mapper;

    @Override
    public CurrencyResponseDto save(CreateCurrencyRequestDto requestDto) {
        Currency currency = mapper.toModel(requestDto);
        Currency currencyFromDb = currencyRepository.save(currency);
        return mapper.toDto(currencyFromDb);
    }
}
