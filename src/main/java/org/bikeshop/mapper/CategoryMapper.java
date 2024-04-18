package org.bikeshop.mapper;

import org.bikeshop.config.MapperConfig;
import org.bikeshop.dto.request.CreateCategoryRequestDto;
import org.bikeshop.dto.response.CategoryResponseDto;
import org.bikeshop.model.Category;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface CategoryMapper {
    CategoryResponseDto toDto(Category category);

    Category toModel(CreateCategoryRequestDto requestDto);
}
