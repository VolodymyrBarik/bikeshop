package org.bikeshop.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.bikeshop.dto.request.CreateCurrencyRequestDto;
import org.bikeshop.service.CurrencyService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/currencies")
public class CurrencyController {
    private final CurrencyService currencyService;

    @Operation(summary = "Create a new currency", description = "Create a new currency")
    @PostMapping
    public void create(@RequestBody @Valid CreateCurrencyRequestDto requestDto) {
        currencyService.save(requestDto);
    }
}
