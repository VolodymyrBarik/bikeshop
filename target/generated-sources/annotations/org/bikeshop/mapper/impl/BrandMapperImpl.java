package org.bikeshop.mapper.impl;

import javax.annotation.processing.Generated;
import org.bikeshop.dto.request.CreateBrandRequestDto;
import org.bikeshop.dto.response.BrandResponseDto;
import org.bikeshop.mapper.BrandMapper;
import org.bikeshop.model.Brand;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-10T17:14:18+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.11 (Oracle Corporation)"
)
@Component
public class BrandMapperImpl implements BrandMapper {

    @Override
    public BrandResponseDto toDto(Brand brand) {
        if ( brand == null ) {
            return null;
        }

        BrandResponseDto brandResponseDto = new BrandResponseDto();

        if ( brand.getId() != null ) {
            brandResponseDto.setId( brand.getId() );
        }
        if ( brand.getName() != null ) {
            brandResponseDto.setName( brand.getName() );
        }
        if ( brand.getDescription() != null ) {
            brandResponseDto.setDescription( brand.getDescription() );
        }
        if ( brand.getLogo() != null ) {
            brandResponseDto.setLogo( brand.getLogo() );
        }

        return brandResponseDto;
    }

    @Override
    public Brand toModel(CreateBrandRequestDto requestDto) {
        if ( requestDto == null ) {
            return null;
        }

        Brand brand = new Brand();

        if ( requestDto.getName() != null ) {
            brand.setName( requestDto.getName() );
        }
        if ( requestDto.getDescription() != null ) {
            brand.setDescription( requestDto.getDescription() );
        }
        if ( requestDto.getLogo() != null ) {
            brand.setLogo( requestDto.getLogo() );
        }

        return brand;
    }
}
