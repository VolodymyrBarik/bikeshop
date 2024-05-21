package org.bikeshop.service;

import java.util.List;
import org.bikeshop.dto.request.CreateStatusRequestDto;
import org.bikeshop.dto.request.StatusRequestDto;
import org.bikeshop.dto.response.StatusResponseDto;
import org.springframework.data.domain.Pageable;

public interface StatusService {
    StatusResponseDto save(CreateStatusRequestDto requestDto);

    List<StatusResponseDto> findAll();

    List<StatusResponseDto> findAllActiveNonDeleted(Pageable pageable);

    StatusResponseDto findById(Long id);

    void update(Long id, CreateStatusRequestDto dto);

    void delete(Long id);

}
