package org.bikeshop.mapper;

import org.bikeshop.config.MapperConfig;
import org.bikeshop.dto.response.OrderStatusHistoryResponseDto;
import org.bikeshop.model.OrderStatusHistory;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface OrderStatusHistoryMapper {

    OrderStatusHistoryResponseDto toResponseDto(OrderStatusHistory orderStatusHistory);
}
