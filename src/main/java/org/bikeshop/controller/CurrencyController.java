package org.bikeshop.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.bikeshop.dto.request.CreateCurrencyRequestDto;
import org.bikeshop.dto.response.CurrencyResponseDto;
import org.bikeshop.service.CurrencyService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
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

    @Operation(summary = "Get all enabled, non deleted currencies",
            description = "Get a list of all enabled, non deleted currencies")
    @GetMapping
    public List<CurrencyResponseDto> getAllEnabledNonDeleted() {
        return currencyService.findAllEnabledNonDeleted();
    }

    @Operation(summary = "Get all currencies",
            description = "Get a list of all currencies including disabled and deleted")
    @GetMapping("/all")
    public List<CurrencyResponseDto> getAll() {
        return currencyService.findAll();
    }

    @Operation(summary = "Get a currency", description = "Returns a currency by it's id")
    @GetMapping("/{id}")
    public CurrencyResponseDto getCurrencyById(@PathVariable Long id) {
        return currencyService.findById(id);
    }

    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "Update a currency", description = "Updates a currency by it's id")
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public void update(@PathVariable Long id,
                       @RequestBody
                       @Valid CreateCurrencyRequestDto requestDto) {
        currencyService.update(id, requestDto);
    }

    @Operation(summary = "Enable a currency", description = "Enables a currency by it's id")
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{id}/enable")
    public void enableCurrency(@PathVariable Long id) {
        currencyService.enable(id);
    }

    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "Disable a currency", description = "Disables a currency by it's id")
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{id}/disable")
    public void disableCurrency(@PathVariable Long id) {
        currencyService.disable(id);
    }

    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "Delete a currency", description = "Deletes a currency by it's id")
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        currencyService.delete(id);
    }

    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "Undelete a currency", description = "Undeletes a currency by it's id")
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{id}/undelete")
    public void undelete(@PathVariable Long id) {
        currencyService.undelete(id);
    }
}
