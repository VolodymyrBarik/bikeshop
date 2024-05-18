package org.bikeshop.service;

import java.util.List;
import org.bikeshop.dto.ProductSearchParameters;
import org.bikeshop.dto.request.CreateProductRequestDto;
import org.bikeshop.dto.response.product.ProductResponseDto;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    ProductResponseDto save(CreateProductRequestDto requestDto);

    List<ProductResponseDto> findAll(Pageable pageable);

    List<ProductResponseDto> findAllWhereQuantityMoreThanZero(Pageable pageable);

    ProductResponseDto findById(Long id);

    void update(Long id, CreateProductRequestDto dto);

    void delete(Long id);

    List<ProductResponseDto> search(ProductSearchParameters searchParameters, Pageable pageable);

    List<ProductResponseDto> searchWhereQuantityMoreThanZero(
            ProductSearchParameters searchParameters, Pageable pageable);
}

