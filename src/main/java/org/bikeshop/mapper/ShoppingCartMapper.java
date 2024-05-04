package org.bikeshop.mapper;

import java.util.Set;
import java.util.stream.Collectors;
import org.bikeshop.config.MapperConfig;
import org.bikeshop.dto.response.CartItemResponseDto;
import org.bikeshop.dto.response.ShoppingCartResponseDto;
import org.bikeshop.model.CartItem;
import org.bikeshop.model.ShoppingCart;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface ShoppingCartMapper {
    @Mapping(source = "shoppingCart.user.id", target = "userId")
    @Mapping(source = "cartItems", target = "cartItemsDto")
    ShoppingCartResponseDto toDto(ShoppingCart shoppingCart);

    default Set<CartItemResponseDto> mapCartItemsSetToCartItemsResponseDtoSet(
            Set<CartItem> cartItems) {
        return cartItems.stream()
                .map(this::mapCartItemToResponseDto)
                .collect(Collectors.toSet());
    }

    default CartItemResponseDto mapCartItemToResponseDto(CartItem cartItem) {
        CartItemResponseDto responseDto = new CartItemResponseDto();
        responseDto.setId(cartItem.getId());
        responseDto.setProductId(cartItem.getProduct().getId());
        responseDto.setProductTitle(cartItem.getProduct().getTitle());
        responseDto.setQuantity(cartItem.getQuantity());
        return responseDto;
    }
}
