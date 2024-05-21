package org.bikeshop.mapper;

import org.bikeshop.config.MapperConfig;
import org.bikeshop.dto.request.CreateStatusRequestDto;
import org.bikeshop.dto.response.StatusResponseDto;
import org.bikeshop.model.Status;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface StatusMapper {
    StatusResponseDto toDto(Status status);

    Status toModel(CreateStatusRequestDto requestDto);
}
