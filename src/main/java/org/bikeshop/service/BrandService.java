package org.bikeshop.service;

import java.util.List;
import org.bikeshop.dto.request.CreateBrandRequestDto;
import org.bikeshop.dto.response.BrandResponseDto;
import org.bikeshop.model.Brand;

public interface BrandService {
    BrandResponseDto save(CreateBrandRequestDto requestDto);

    BrandResponseDto getById(Long id);

    List<BrandResponseDto> findAll();

    BrandResponseDto update(Long id, Brand brand);

    void deleteById(Long id);
}
