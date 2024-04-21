package org.bikeshop.service;

import java.util.List;
import org.bikeshop.dto.request.CreateCategoryRequestDto;
import org.bikeshop.dto.response.CategoryResponseDto;

public interface CategoryService {
    CategoryResponseDto save(CreateCategoryRequestDto requestDto);

    CategoryResponseDto findById(Long id);

    List<CategoryResponseDto> findAllEnabledNonDisabled();

    List<CategoryResponseDto> findAll();

    CategoryResponseDto update(Long id, CreateCategoryRequestDto requestDto);

    void enableCategory(Long id);

    void disableCategoryById(Long id);

    void deleteById(Long id);

    void undeleteById(Long id);
}
