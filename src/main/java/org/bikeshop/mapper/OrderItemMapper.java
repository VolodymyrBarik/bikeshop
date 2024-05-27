package org.bikeshop.mapper;

import org.bikeshop.config.MapperConfig;
import org.bikeshop.dto.response.OrderItemResponseDto;
import org.bikeshop.model.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface OrderItemMapper {
    @Mapping(source = "product.id", target = "productId")
    OrderItemResponseDto toResponseDto(OrderItem orderItem);
}
