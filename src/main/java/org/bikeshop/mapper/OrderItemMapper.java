package org.bikeshop.mapper;

import org.bikeshop.config.MapperConfig;
import org.bikeshop.dto.response.OrderItemResponseDto;
import org.bikeshop.model.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class, componentModel = "spring")
public interface OrderItemMapper {
    @Mapping(source = "product.id", target = "productId")
    @Mapping(source = "orderItem.product.title", target = "title")
    @Mapping(source = "orderItem.product.id", target = "id")
    OrderItemResponseDto toResponseDto(OrderItem orderItem);
}
