package org.bikeshop.mapper.impl;

import javax.annotation.processing.Generated;
import org.bikeshop.dto.request.CreateCurrencyRequestDto;
import org.bikeshop.dto.response.CurrencyResponseDto;
import org.bikeshop.mapper.CurrencyMapper;
import org.bikeshop.model.Currency;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-05T21:41:05+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.11 (Oracle Corporation)"
)
@Component
public class CurrencyMapperImpl implements CurrencyMapper {

    @Override
    public CurrencyResponseDto toDto(Currency currency) {
        if ( currency == null ) {
            return null;
        }

        CurrencyResponseDto currencyResponseDto = new CurrencyResponseDto();

        if ( currency.getId() != null ) {
            currencyResponseDto.setId( currency.getId() );
        }
        if ( currency.getName() != null ) {
            currencyResponseDto.setName( currency.getName() );
        }
        if ( currency.getExchangeRate() != null ) {
            currencyResponseDto.setExchangeRate( currency.getExchangeRate().doubleValue() );
        }
        currencyResponseDto.setDeleted( currency.isDeleted() );
        currencyResponseDto.setEnabled( currency.isEnabled() );

        return currencyResponseDto;
    }

    @Override
    public Currency toModel(CreateCurrencyRequestDto requestDto) {
        if ( requestDto == null ) {
            return null;
        }

        Currency currency = new Currency();

        if ( requestDto.getName() != null ) {
            currency.setName( requestDto.getName() );
        }
        if ( requestDto.getExchangeRate() != null ) {
            currency.setExchangeRate( requestDto.getExchangeRate() );
        }

        return currency;
    }
}
