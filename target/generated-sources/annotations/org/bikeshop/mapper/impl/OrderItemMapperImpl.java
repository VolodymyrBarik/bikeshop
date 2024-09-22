package org.bikeshop.mapper.impl;

import javax.annotation.processing.Generated;
import org.bikeshop.dto.response.OrderItemResponseDto;
import org.bikeshop.mapper.OrderItemMapper;
import org.bikeshop.model.OrderItem;
import org.bikeshop.model.Product;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-22T20:07:02+0300",
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
        String title = orderItemProductTitle( orderItem );
        if ( title != null ) {
            orderItemResponseDto.setTitle( title );
        }
        Long id1 = orderItemProductId( orderItem );
        if ( id1 != null ) {
            orderItemResponseDto.setId( id1 );
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

    private String orderItemProductTitle(OrderItem orderItem) {
        if ( orderItem == null ) {
            return null;
        }
        Product product = orderItem.getProduct();
        if ( product == null ) {
            return null;
        }
        String title = product.getTitle();
        if ( title == null ) {
            return null;
        }
        return title;
    }
}
