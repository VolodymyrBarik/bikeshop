package org.bikeshop.mapper;

import org.bikeshop.config.MapperConfig;
import org.bikeshop.dto.response.OrderStatusHistoryResponseDto;
import org.bikeshop.model.OrderStatusHistory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface OrderStatusHistoryMapper {

    @Mapping(source = "status.name", target = "statusName")
    @Mapping(source = "order.id", target = "orderId")
    OrderStatusHistoryResponseDto toResponseDto(OrderStatusHistory orderStatusHistory);
}
