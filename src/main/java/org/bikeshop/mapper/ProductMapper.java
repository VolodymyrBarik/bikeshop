package org.bikeshop.mapper;

import org.bikeshop.config.MapperConfig;
import org.bikeshop.dto.request.CreateProductRequestDto;
import org.bikeshop.dto.response.ProductResponseDto;
import org.bikeshop.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface ProductMapper {
    ProductResponseDto toDto(Product product);

    @Mapping(source = "requestDto.currencyId", target = "currency.id")
    @Mapping(source = "requestDto.brandId", target = "brand.id")
    @Mapping(source = "requestDto.categoryId", target = "category.id")
    Product toModel(CreateProductRequestDto requestDto);
}
