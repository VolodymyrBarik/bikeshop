package org.bikeshop.mapper;

import org.bikeshop.config.MapperConfig;
import org.bikeshop.dto.request.CreateBrandRequestDto;
import org.bikeshop.dto.response.BrandResponseDto;
import org.bikeshop.model.Brand;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface BrandMapper {
    BrandResponseDto toDto(Brand brand);

    Brand toModel(CreateBrandRequestDto requestDto);
}
