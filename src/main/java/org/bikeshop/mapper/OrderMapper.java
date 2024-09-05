package org.bikeshop.mapper;


import org.bikeshop.config.MapperConfig;
import org.bikeshop.dto.response.OrderListDto;
import org.bikeshop.dto.response.OrderResponseDto;
import org.bikeshop.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface OrderMapper {
    @Mapping(source = "order.user.id", target = "userId")
    @Mapping(source = "order.currentStatus.name", target = "currentStatus")
    OrderResponseDto toDto(Order order);

    @Mapping(source = "order.user.id", target = "userId")
    @Mapping(source = "order.currentStatus.name", target = "currentStatus")
    @Mapping(source = "order.currentStatus.id", target = "statusId")
    @Mapping(source = "order.orderDate", target = "orderDateTime")
    @Mapping(target = "isPaid", expression = "java(order.isPaid())")
    OrderListDto toListDto(Order order);
}
