package org.bikeshop.mapper.impl;

import javax.annotation.processing.Generated;
import org.bikeshop.dto.response.OrderStatusHistoryResponseDto;
import org.bikeshop.mapper.OrderStatusHistoryMapper;
import org.bikeshop.model.Order;
import org.bikeshop.model.OrderStatusHistory;
import org.bikeshop.model.Status;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-12-29T20:38:54+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.11 (Oracle Corporation)"
)
@Component
public class OrderStatusHistoryMapperImpl implements OrderStatusHistoryMapper {

    @Override
    public OrderStatusHistoryResponseDto toResponseDto(OrderStatusHistory orderStatusHistory) {
        if ( orderStatusHistory == null ) {
            return null;
        }

        OrderStatusHistoryResponseDto orderStatusHistoryResponseDto = new OrderStatusHistoryResponseDto();

        String name = orderStatusHistoryStatusName( orderStatusHistory );
        if ( name != null ) {
            orderStatusHistoryResponseDto.setStatusName( name );
        }
        Long id = orderStatusHistoryOrderId( orderStatusHistory );
        if ( id != null ) {
            orderStatusHistoryResponseDto.setOrderId( id );
        }
        if ( orderStatusHistory.getId() != null ) {
            orderStatusHistoryResponseDto.setId( orderStatusHistory.getId() );
        }
        if ( orderStatusHistory.getTimestamp() != null ) {
            orderStatusHistoryResponseDto.setTimestamp( orderStatusHistory.getTimestamp() );
        }
        if ( orderStatusHistory.getLogin() != null ) {
            orderStatusHistoryResponseDto.setLogin( orderStatusHistory.getLogin() );
        }
        if ( orderStatusHistory.getComment() != null ) {
            orderStatusHistoryResponseDto.setComment( orderStatusHistory.getComment() );
        }

        return orderStatusHistoryResponseDto;
    }

    private String orderStatusHistoryStatusName(OrderStatusHistory orderStatusHistory) {
        if ( orderStatusHistory == null ) {
            return null;
        }
        Status status = orderStatusHistory.getStatus();
        if ( status == null ) {
            return null;
        }
        String name = status.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    private Long orderStatusHistoryOrderId(OrderStatusHistory orderStatusHistory) {
        if ( orderStatusHistory == null ) {
            return null;
        }
        Order order = orderStatusHistory.getOrder();
        if ( order == null ) {
            return null;
        }
        Long id = order.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
