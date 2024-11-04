package org.bikeshop.mapper.impl;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.bikeshop.dto.response.CartItemResponseDto;
import org.bikeshop.dto.response.ShoppingCartResponseDto;
import org.bikeshop.mapper.ShoppingCartMapper;
import org.bikeshop.model.CartItem;
import org.bikeshop.model.ShoppingCart;
import org.bikeshop.model.User;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-04T21:51:00+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.11 (Oracle Corporation)"
)
@Component
public class ShoppingCartMapperImpl implements ShoppingCartMapper {

    @Override
    public ShoppingCartResponseDto toDto(ShoppingCart shoppingCart) {
        if ( shoppingCart == null ) {
            return null;
        }

        ShoppingCartResponseDto shoppingCartResponseDto = new ShoppingCartResponseDto();

        Long id = shoppingCartUserId( shoppingCart );
        if ( id != null ) {
            shoppingCartResponseDto.setUserId( id );
        }
        Set<CartItemResponseDto> set = cartItemListToCartItemResponseDtoSet( shoppingCart.getCartItems() );
        if ( set != null ) {
            shoppingCartResponseDto.setCartItemsDto( set );
        }
        if ( shoppingCart.getId() != null ) {
            shoppingCartResponseDto.setId( shoppingCart.getId() );
        }

        return shoppingCartResponseDto;
    }

    private Long shoppingCartUserId(ShoppingCart shoppingCart) {
        if ( shoppingCart == null ) {
            return null;
        }
        User user = shoppingCart.getUser();
        if ( user == null ) {
            return null;
        }
        Long id = user.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected Set<CartItemResponseDto> cartItemListToCartItemResponseDtoSet(List<CartItem> list) {
        if ( list == null ) {
            return null;
        }

        Set<CartItemResponseDto> set = new LinkedHashSet<CartItemResponseDto>( Math.max( (int) ( list.size() / .75f ) + 1, 16 ) );
        for ( CartItem cartItem : list ) {
            set.add( mapCartItemToResponseDto( cartItem ) );
        }

        return set;
    }
}
