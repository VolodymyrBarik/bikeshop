package org.bikeshop.mapper;

import org.bikeshop.config.MapperConfig;
import org.bikeshop.dto.response.WholesaleUserResponseDto;
import org.bikeshop.model.WholesaleUser;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface WholesaleUserMapper {
    WholesaleUserResponseDto toDto(WholesaleUser wholesaleUser);
}
