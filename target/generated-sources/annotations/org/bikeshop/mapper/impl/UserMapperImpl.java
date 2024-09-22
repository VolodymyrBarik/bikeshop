package org.bikeshop.mapper.impl;

import javax.annotation.processing.Generated;
import org.bikeshop.dto.response.UserResponseDto;
import org.bikeshop.mapper.UserMapper;
import org.bikeshop.model.User;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-22T20:07:02+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.11 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserResponseDto toDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserResponseDto userResponseDto = new UserResponseDto();

        if ( user.getId() != null ) {
            userResponseDto.setId( user.getId() );
        }
        if ( user.getEmail() != null ) {
            userResponseDto.setEmail( user.getEmail() );
        }
        if ( user.getFirstName() != null ) {
            userResponseDto.setFirstName( user.getFirstName() );
        }
        if ( user.getLastName() != null ) {
            userResponseDto.setLastName( user.getLastName() );
        }
        if ( user.getShippingAddress() != null ) {
            userResponseDto.setShippingAddress( user.getShippingAddress() );
        }
        if ( user.getCompanyName() != null ) {
            userResponseDto.setCompanyName( user.getCompanyName() );
        }

        return userResponseDto;
    }
}
