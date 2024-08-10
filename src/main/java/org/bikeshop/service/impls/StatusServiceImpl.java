package org.bikeshop.service.impls;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.bikeshop.dto.request.CreateStatusRequestDto;
import org.bikeshop.dto.response.StatusResponseDto;
import org.bikeshop.exception.EntityNotFoundException;
import org.bikeshop.mapper.StatusMapper;
import org.bikeshop.model.Status;
import org.bikeshop.repository.StatusRepository;
import org.bikeshop.service.StatusService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StatusServiceImpl implements StatusService {
    private final StatusRepository statusRepository;
    private final StatusMapper mapper;

    @Override
    public StatusResponseDto save(CreateStatusRequestDto requestDto) {
        Status status = mapper.toModel(requestDto);
        Status statusFromDb = statusRepository.save(status);
        return mapper.toDto(statusFromDb);
    }

    @Override
    public List<StatusResponseDto> findAll() {
        return statusRepository.findAll().stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public List<StatusResponseDto> findAllActiveNonDeleted(Pageable pageable) {
        return statusRepository.findAllActiveNonDeleted(pageable)
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public StatusResponseDto findById(Long id) {
        Status statusFromDb = statusRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Can't find status by id " + id));
        return mapper.toDto(statusFromDb);
    }

    @Override
    public void update(Long id, CreateStatusRequestDto dto) {
        Status statusFromDb = statusRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Can't find status by id " + id));
        statusFromDb.setName(dto.getName());
        statusFromDb.setDescription(dto.getMessage());
        statusFromDb.setActive(dto.isActive());
        statusFromDb.setDeleted(dto.isDeleted());
        statusRepository.save(statusFromDb);
    }

    @Override
    public void delete(Long id) {
        statusRepository.deleteById(id);
    }

    @Override
    public void enableStatus(Long id) {
        Status statusFromDb = statusRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Can't find status by id " + id));
        statusFromDb.setActive(true);
        statusRepository.save(statusFromDb);
    }

    @Override
    public void disableStatus(Long id) {
        Status statusFromDb = statusRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Can't find status by id " + id));
        statusFromDb.setActive(false);
        statusRepository.save(statusFromDb);
    }
}
