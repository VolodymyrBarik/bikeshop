package org.bikeshop.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.bikeshop.dto.request.CreateProductRequestDto;
import org.bikeshop.dto.response.ProductResponseDto;
import org.bikeshop.service.BrandService;
import org.bikeshop.service.ProductService;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final CurrencyService currencyService;
    private final CategoryService categoryService;
    private final BrandService brandService;

    @PreAuthorize("isAuthenticated() and hasRole('ADMIN')")
    @Operation(summary = "Create a new product", description = "Create a new product")
    @PostMapping
    public ProductResponseDto create(@RequestBody @Valid CreateProductRequestDto requestDto) {
        return productService.save(requestDto);
    }


//    @Operation(summary = "Get all products", description = "Get a list of all available products")
//    @GetMapping
//    public List<ProductResponseDto> getAll(Pageable pageable) {
//        return productService.findAll(pageable);
//    }
}
