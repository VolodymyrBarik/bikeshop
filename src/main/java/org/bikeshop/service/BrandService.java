package org.bikeshop.service;

import java.util.List;
import java.util.Optional;
import org.bikeshop.dto.request.BrandRequestDto;
import org.bikeshop.dto.response.BrandResponseDto;
import org.bikeshop.model.Brand;

public interface BrandService {
    BrandResponseDto save(BrandRequestDto requestDto);

    Optional<Brand> findById(Long id);

    List<BrandResponseDto> findAll();

    BrandResponseDto update(Long id, Brand brand);

    void deleteById(Long id);
}
