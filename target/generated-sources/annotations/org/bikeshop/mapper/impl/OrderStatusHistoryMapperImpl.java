package org.bikeshop.mapper.impl;

import javax.annotation.processing.Generated;
import org.bikeshop.dto.response.OrderStatusHistoryResponseDto;
import org.bikeshop.mapper.OrderStatusHistoryMapper;
import org.bikeshop.model.OrderStatusHistory;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-13T19:07:29+0300",
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
}
