package org.bikeshop.service;

import java.util.List;
import org.bikeshop.dto.request.CreateCategoryRequestDto;
import org.bikeshop.dto.response.CategoryResponseDto;
import org.springframework.data.domain.Pageable;

public interface CategoryService {
    CategoryResponseDto save(CreateCategoryRequestDto requestDto);

    CategoryResponseDto findById(Long id);

    List<CategoryResponseDto> findAllEnabledNonDeleted(Pageable pageable);

    List<CategoryResponseDto> findAll(Pageable pageable);

    CategoryResponseDto update(Long id, CreateCategoryRequestDto requestDto);

    void enableCategory(Long id);

    void disableCategoryById(Long id);

    void deleteById(Long id);

    void undeleteById(Long id);
}
