package org.bikeshop.service;

import java.util.List;

import org.bikeshop.dto.ProductSearchParameters;
import org.bikeshop.dto.request.CreateProductRequestDto;
import org.bikeshop.dto.response.product.ProductResponseDto;
import org.bikeshop.model.Product;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    ProductResponseDto save(CreateProductRequestDto requestDto);

    List<ProductResponseDto> findAllIncludingDisabled(Pageable pageable);

    List<ProductResponseDto> findAllEnabled(Pageable pageable);

    ProductResponseDto findById(Long id);

    List<Product> findAllByCurrencyId(Long currencyId);

    void update(Long id, CreateProductRequestDto dto);

    void delete(Long id);

    List<ProductResponseDto> search(ProductSearchParameters searchParameters, Pageable pageable);

    void updatePriceByCurrency(Long currencyId);

    void enable(Long id);

    void disable(Long id);

    void undelete(Long id);


}

