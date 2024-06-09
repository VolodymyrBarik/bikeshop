package org.bikeshop.service.impls;

import java.util.List;
import java.util.function.Predicate;
import lombok.RequiredArgsConstructor;
import org.bikeshop.dto.request.CreateCurrencyRequestDto;
import org.bikeshop.dto.request.ExchangeRateRequestDto;
import org.bikeshop.dto.response.CurrencyResponseDto;
import org.bikeshop.exception.EntityNotFoundException;
import org.bikeshop.mapper.CurrencyMapper;
import org.bikeshop.model.Currency;
import org.bikeshop.repository.CurrencyRepository;
import org.bikeshop.service.CurrencyService;
import org.bikeshop.service.ProductService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CurrencyServiceImpl implements CurrencyService {
    private final CurrencyRepository currencyRepository;
    private final CurrencyMapper mapper;
    private final ProductService productService;

    @Override
    public CurrencyResponseDto save(CreateCurrencyRequestDto requestDto) {
        Currency currency = mapper.toModel(requestDto);
        Currency currencyFromDb = currencyRepository.save(currency);
        return mapper.toDto(currencyFromDb);
    }

    @Override
    public CurrencyResponseDto findById(Long id) {
        Currency currencyFromDb = currencyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Can't find currency by id " + id));
        return mapper.toDto(currencyFromDb);
    }

    @Override
    public List<CurrencyResponseDto> findAll() {
        return currencyRepository.findAll().stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public List<CurrencyResponseDto> findAllEnabledNonDeleted() {
        return currencyRepository.findAll().stream()
                .filter(Currency::isEnabled)
                .filter(Predicate.not(Currency::isDeleted))
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public void update(Long id, CreateCurrencyRequestDto requestDto) {
        Currency currencyFromDb = currencyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Can't find currency by id " + id));
        currencyFromDb.setName(requestDto.getName());
        currencyFromDb.setExchangeRate(requestDto.getExchangeRate());
        currencyRepository.save(currencyFromDb);
    }

    @Override
    public void updateRate(Long id, ExchangeRateRequestDto requestDto) {
        Currency currencyFromDb = currencyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Can't find currency by id " + id));
        currencyFromDb.setExchangeRate(requestDto.getExchangeRate());
        currencyRepository.save(currencyFromDb);
        productService.updatePriceByCurrency(id);
    }

    @Override
    public void delete(Long id) {
        currencyRepository.deleteById(id);
    }

    @Override
    public void undelete(Long id) {
        Currency currencyFromDb = currencyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Can't find currency by id " + id));
        currencyFromDb.setDeleted(false);
        currencyRepository.save(currencyFromDb);
    }

    @Override
    public void enable(Long id) {
        Currency currencyFromDb = currencyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Can't find currency by id " + id));
        currencyFromDb.setEnabled(true);
        currencyRepository.save(currencyFromDb);
    }

    @Override
    public void disable(Long id) {
        Currency currencyFromDb = currencyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Can't find currency by id " + id));
        currencyFromDb.setEnabled(false);
        currencyRepository.save(currencyFromDb);
    }
}
