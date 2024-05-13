package org.bikeshop.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.bikeshop.dto.ProductSearchParameters;
import org.bikeshop.dto.request.CreateProductRequestDto;
import org.bikeshop.dto.response.BrandResponseDto;
import org.bikeshop.dto.response.product.ProductResponseDto;
import org.bikeshop.service.ProductService;
import org.springframework.data.domain.Pageable;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    //@PreAuthorize("isAuthenticated() and hasRole('ADMIN')")
    @Operation(summary = "Create a new product", description = "Create a new product")
    @PostMapping
    public ProductResponseDto create(@RequestBody @Valid CreateProductRequestDto requestDto) {
        return productService.save(requestDto);
    }


    @Operation(summary = "Get all products", description = "Get a list of all available products")
    @GetMapping
    public List<ProductResponseDto> getAll(Pageable pageable) {
        return productService.findAll(pageable);
    }

    @Operation(summary = "Update a product", description = "Updates a product by it's id")
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public void update(@PathVariable Long id,
                       @RequestBody
                       @Valid CreateProductRequestDto requestDto) {
        productService.update(id, requestDto);
    }

    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "Delete a product", description = "Deletes a product by it's id")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }

    @Operation(summary = "Get a product", description = "Returns a product by it's id")
    @GetMapping("/{id}")
    public ProductResponseDto getProductById(@PathVariable Long id) {
        return productService.findById(id);
    }

    @Operation(summary = "Search a product", description = "Search a product by brand and category")
    @GetMapping("/search")
    public List<ProductResponseDto> search(ProductSearchParameters searchParameters, Pageable pageable) {
        return productService.search(searchParameters, pageable);
    }
}
