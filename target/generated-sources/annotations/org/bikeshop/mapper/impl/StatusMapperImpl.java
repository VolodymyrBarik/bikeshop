package org.bikeshop.mapper.impl;

import javax.annotation.processing.Generated;
import org.bikeshop.dto.request.CreateStatusRequestDto;
import org.bikeshop.dto.response.StatusResponseDto;
import org.bikeshop.mapper.StatusMapper;
import org.bikeshop.model.Status;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-13T19:07:29+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.11 (Oracle Corporation)"
)
@Component
public class StatusMapperImpl implements StatusMapper {

    @Override
    public StatusResponseDto toDto(Status status) {
        if ( status == null ) {
            return null;
        }

        StatusResponseDto statusResponseDto = new StatusResponseDto();

        if ( status.getId() != null ) {
            statusResponseDto.setId( status.getId() );
        }
        if ( status.getName() != null ) {
            statusResponseDto.setName( status.getName() );
        }
        statusResponseDto.setActive( status.isActive() );
        statusResponseDto.setDeleted( status.isDeleted() );

        return statusResponseDto;
    }

    @Override
    public Status toModel(CreateStatusRequestDto requestDto) {
        if ( requestDto == null ) {
            return null;
        }

        Status status = new Status();

        if ( requestDto.getName() != null ) {
            status.setName( requestDto.getName() );
        }
        status.setActive( requestDto.isActive() );
        status.setDeleted( requestDto.isDeleted() );

        return status;
    }
}
