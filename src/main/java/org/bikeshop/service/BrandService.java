package org.bikeshop.service;

import java.util.List;
import org.bikeshop.dto.request.CreateBrandRequestDto;
import org.bikeshop.dto.response.BrandResponseDto;

public interface BrandService {
    BrandResponseDto save(CreateBrandRequestDto requestDto);

    BrandResponseDto findById(Long id);

    List<BrandResponseDto> findAllEnabledNonDisabled();

    List<BrandResponseDto> findAll();

    BrandResponseDto update(Long id, CreateBrandRequestDto requestDto);

    void enableBrand(Long id);

    void disableBrand(Long id);

    void deleteById(Long id);

    void undeleteById(Long id);
}
