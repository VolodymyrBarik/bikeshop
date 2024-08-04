package org.bikeshop.mapper.impl;

import javax.annotation.processing.Generated;
import org.bikeshop.dto.response.OrderItemResponseDto;
import org.bikeshop.mapper.OrderItemMapper;
import org.bikeshop.model.OrderItem;
import org.bikeshop.model.Product;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-04T21:15:00+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.11 (Oracle Corporation)"
)
@Component
public class OrderItemMapperImpl implements OrderItemMapper {

    @Override
    public OrderItemResponseDto toResponseDto(OrderItem orderItem) {
        if ( orderItem == null ) {
            return null;
        }

        OrderItemResponseDto orderItemResponseDto = new OrderItemResponseDto();

        Long id = orderItemProductId( orderItem );
        if ( id != null ) {
            orderItemResponseDto.setProductId( id );
        }
        if ( orderItem.getId() != null ) {
            orderItemResponseDto.setId( orderItem.getId() );
        }
        if ( orderItem.getPrice() != null ) {
            orderItemResponseDto.setPrice( orderItem.getPrice().longValue() );
        }
        orderItemResponseDto.setQuantity( orderItem.getQuantity() );

        return orderItemResponseDto;
    }

    private Long orderItemProductId(OrderItem orderItem) {
        if ( orderItem == null ) {
            return null;
        }
        Product product = orderItem.getProduct();
        if ( product == null ) {
            return null;
        }
        Long id = product.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
