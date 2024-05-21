package org.bikeshop.mapper;

import org.bikeshop.dto.response.OrderItemResponseDto;
import org.bikeshop.model.OrderItem;
import org.mapstruct.Mapping;

public interface OrderItemMapper {
    @Mapping(source = "product.id", target = "productId")
    OrderItemResponseDto toResponseDto(OrderItem orderItem);
}
