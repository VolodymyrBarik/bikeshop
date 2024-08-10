package org.bikeshop.mapper.impl;

import java.util.Set;
import javax.annotation.processing.Generated;
import org.bikeshop.dto.response.CartItemResponseDto;
import org.bikeshop.dto.response.ShoppingCartResponseDto;
import org.bikeshop.mapper.ShoppingCartMapper;
import org.bikeshop.model.ShoppingCart;
import org.bikeshop.model.User;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-10T17:14:18+0300",
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
        Set<CartItemResponseDto> set = mapCartItemsSetToCartItemsResponseDtoSet( shoppingCart.getCartItems() );
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
}
