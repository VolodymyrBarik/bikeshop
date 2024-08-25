package org.bikeshop.mapper.impl;

import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.bikeshop.dto.response.OrderItemResponseDto;
import org.bikeshop.dto.response.OrderListDto;
import org.bikeshop.dto.response.OrderResponseDto;
import org.bikeshop.mapper.OrderMapper;
import org.bikeshop.model.Order;
import org.bikeshop.model.OrderItem;
import org.bikeshop.model.Status;
import org.bikeshop.model.User;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-25T20:53:00+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.11 (Oracle Corporation)"
)
@Component
public class OrderMapperImpl implements OrderMapper {

    @Override
    public OrderResponseDto toDto(Order order) {
        if ( order == null ) {
            return null;
        }

        OrderResponseDto orderResponseDto = new OrderResponseDto();

        Long id = orderUserId( order );
        if ( id != null ) {
            orderResponseDto.setUserId( id );
        }
        String name = orderCurrentStatusName( order );
        if ( name != null ) {
            orderResponseDto.setCurrentStatus( name );
        }
        if ( order.getId() != null ) {
            orderResponseDto.setId( order.getId() );
        }
        Set<OrderItemResponseDto> set = orderItemSetToOrderItemResponseDtoSet( order.getOrderItems() );
        if ( set != null ) {
            orderResponseDto.setOrderItems( set );
        }
        if ( order.getTotal() != null ) {
            orderResponseDto.setTotal( order.getTotal() );
        }
        orderResponseDto.setDeleted( order.isDeleted() );

        return orderResponseDto;
    }

    @Override
    public OrderListDto toListDto(Order order) {
        if ( order == null ) {
            return null;
        }

        OrderListDto orderListDto = new OrderListDto();

        Long id = orderUserId( order );
        if ( id != null ) {
            orderListDto.setUserId( id );
        }
        String name = orderCurrentStatusName( order );
        if ( name != null ) {
            orderListDto.setCurrentStatus( name );
        }
        if ( order.getId() != null ) {
            orderListDto.setId( order.getId() );
        }
        if ( order.getTotal() != null ) {
            orderListDto.setTotal( order.getTotal() );
        }
        orderListDto.setDeleted( order.isDeleted() );

        return orderListDto;
    }

    private Long orderUserId(Order order) {
        if ( order == null ) {
            return null;
        }
        User user = order.getUser();
        if ( user == null ) {
            return null;
        }
        Long id = user.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String orderCurrentStatusName(Order order) {
        if ( order == null ) {
            return null;
        }
        Status currentStatus = order.getCurrentStatus();
        if ( currentStatus == null ) {
            return null;
        }
        String name = currentStatus.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    protected OrderItemResponseDto orderItemToOrderItemResponseDto(OrderItem orderItem) {
        if ( orderItem == null ) {
            return null;
        }

        OrderItemResponseDto orderItemResponseDto = new OrderItemResponseDto();

        if ( orderItem.getId() != null ) {
            orderItemResponseDto.setId( orderItem.getId() );
        }
        if ( orderItem.getPrice() != null ) {
            orderItemResponseDto.setPrice( orderItem.getPrice().longValue() );
        }
        orderItemResponseDto.setQuantity( orderItem.getQuantity() );

        return orderItemResponseDto;
    }

    protected Set<OrderItemResponseDto> orderItemSetToOrderItemResponseDtoSet(Set<OrderItem> set) {
        if ( set == null ) {
            return null;
        }

        Set<OrderItemResponseDto> set1 = new LinkedHashSet<OrderItemResponseDto>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( OrderItem orderItem : set ) {
            set1.add( orderItemToOrderItemResponseDto( orderItem ) );
        }

        return set1;
    }
}
