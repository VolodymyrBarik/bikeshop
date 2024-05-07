package org.bikeshop.mapper;

import org.bikeshop.config.MapperConfig;
import org.bikeshop.dto.response.UserResponseDto;
import org.bikeshop.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",config = MapperConfig.class)
public interface UserMapper {
    UserResponseDto toDto(User user);
}
