package org.bikeshop.service;

import org.bikeshop.dto.request.CreateStatusRequestDto;
import org.bikeshop.dto.response.StatusResponseDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StatusService {
    StatusResponseDto save(CreateStatusRequestDto requestDto);

    List<StatusResponseDto> findAll();

    List<StatusResponseDto> findAllActiveNonDeleted(Pageable pageable);

    StatusResponseDto findById(Long id);

    void update(Long id, CreateStatusRequestDto dto);

    void delete(Long id);

    void enableStatus(Long id);

    void disableStatus(Long id);
}
