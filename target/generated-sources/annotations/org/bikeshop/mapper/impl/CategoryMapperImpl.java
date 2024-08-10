package org.bikeshop.mapper.impl;

import javax.annotation.processing.Generated;
import org.bikeshop.dto.request.CreateCategoryRequestDto;
import org.bikeshop.dto.response.CategoryResponseDto;
import org.bikeshop.mapper.CategoryMapper;
import org.bikeshop.model.Category;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-10T17:14:18+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.11 (Oracle Corporation)"
)
@Component
public class CategoryMapperImpl implements CategoryMapper {

    @Override
    public CategoryResponseDto toDto(Category category) {
        if ( category == null ) {
            return null;
        }

        CategoryResponseDto categoryResponseDto = new CategoryResponseDto();

        if ( category.getId() != null ) {
            categoryResponseDto.setId( category.getId() );
        }
        if ( category.getName() != null ) {
            categoryResponseDto.setName( category.getName() );
        }
        if ( category.getDescription() != null ) {
            categoryResponseDto.setDescription( category.getDescription() );
        }

        return categoryResponseDto;
    }

    @Override
    public Category toModel(CreateCategoryRequestDto requestDto) {
        if ( requestDto == null ) {
            return null;
        }

        Category category = new Category();

        if ( requestDto.getName() != null ) {
            category.setName( requestDto.getName() );
        }
        if ( requestDto.getDescription() != null ) {
            category.setDescription( requestDto.getDescription() );
        }
        if ( requestDto.getLogo() != null ) {
            category.setLogo( requestDto.getLogo() );
        }

        return category;
    }
}
