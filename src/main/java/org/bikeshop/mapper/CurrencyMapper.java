package org.bikeshop.mapper;

import org.bikeshop.config.MapperConfig;
import org.bikeshop.dto.request.CreateCurrencyRequestDto;
import org.bikeshop.dto.response.CurrencyResponseDto;
import org.bikeshop.model.Currency;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface CurrencyMapper {
    CurrencyResponseDto toDto(Currency currency);

    Currency toModel(CreateCurrencyRequestDto requestDto);
}
