package org.bikeshop.service;

import org.bikeshop.dto.request.CreateCategoryRequestDto;
import org.bikeshop.dto.response.CategoryResponseDto;

public interface CategoryService {
    CategoryResponseDto save(CreateCategoryRequestDto requestDto);
}
