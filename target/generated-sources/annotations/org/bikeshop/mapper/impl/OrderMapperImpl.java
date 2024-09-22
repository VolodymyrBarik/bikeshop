package org.bikeshop.mapper.impl;

import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.bikeshop.dto.response.OrderItemResponseDto;
import org.bikeshop.dto.response.OrderListDto;
import org.bikeshop.dto.response.OrderResponseDto;
import org.bikeshop.mapper.OrderItemMapper;
import org.bikeshop.mapper.OrderMapper;
import org.bikeshop.model.Order;
import org.bikeshop.model.OrderItem;
import org.bikeshop.model.Status;
import org.bikeshop.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-22T20:07:02+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.11 (Oracle Corporation)"
)
@Component
public class OrderMapperImpl implements OrderMapper {

    private final OrderItemMapper orderItemMapper;

    @Autowired
    public OrderMapperImpl(OrderItemMapper orderItemMapper) {

        this.orderItemMapper = orderItemMapper;
    }

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
        String companyName = orderUserCompanyName( order );
        if ( companyName != null ) {
            orderResponseDto.setCompanyName( companyName );
        }
        if ( order.getOrderDate() != null ) {
            orderResponseDto.setOrderDateTime( order.getOrderDate() );
        }
        if ( order.getId() != null ) {
            orderResponseDto.setId( order.getId() );
        }
        Set<OrderItemResponseDto> set = orderItemSetToOrderItemResponseDtoSet( order.getOrderItems() );
        if ( set != null ) {
            orderResponseDto.setOrderItems( set );
        }
        if ( order.getShippingAddress() != null ) {
            orderResponseDto.setShippingAddress( order.getShippingAddress() );
        }
        if ( order.getAdditionalComment() != null ) {
            orderResponseDto.setAdditionalComment( order.getAdditionalComment() );
        }
        if ( order.getTotal() != null ) {
            orderResponseDto.setTotal( order.getTotal() );
        }

        orderResponseDto.setUser( order.getUser().getFirstName() + ' ' + order.getUser().getLastName() );
        orderResponseDto.setIsPaid( order.isPaid() );

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
        Long id1 = orderCurrentStatusId( order );
        if ( id1 != null ) {
            orderListDto.setStatusId( id1 );
        }
        if ( order.getOrderDate() != null ) {
            orderListDto.setOrderDateTime( order.getOrderDate() );
        }
        if ( order.getId() != null ) {
            orderListDto.setId( order.getId() );
        }
        if ( order.getTotal() != null ) {
            orderListDto.setTotal( order.getTotal() );
        }
        orderListDto.setDeleted( order.isDeleted() );

        orderListDto.setIsPaid( order.isPaid() );

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

    private String orderUserCompanyName(Order order) {
        if ( order == null ) {
            return null;
        }
        User user = order.getUser();
        if ( user == null ) {
            return null;
        }
        String companyName = user.getCompanyName();
        if ( companyName == null ) {
            return null;
        }
        return companyName;
    }

    protected Set<OrderItemResponseDto> orderItemSetToOrderItemResponseDtoSet(Set<OrderItem> set) {
        if ( set == null ) {
            return null;
        }

        Set<OrderItemResponseDto> set1 = new LinkedHashSet<OrderItemResponseDto>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( OrderItem orderItem : set ) {
            set1.add( orderItemMapper.toResponseDto( orderItem ) );
        }

        return set1;
    }

    private Long orderCurrentStatusId(Order order) {
        if ( order == null ) {
            return null;
        }
        Status currentStatus = order.getCurrentStatus();
        if ( currentStatus == null ) {
            return null;
        }
        Long id = currentStatus.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
