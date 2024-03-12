package org.bikeshop.service;

import java.util.List;
import java.util.Optional;
import jakarta.persistence.EntityNotFoundException;
import org.bikeshop.dto.request.BrandRequestDto;
import org.bikeshop.dto.response.BrandResponseDto;
import org.bikeshop.mapper.BrandMapper;
import org.bikeshop.model.Brand;
import org.bikeshop.repository.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {
    private final BrandRepository brandRepository;
    private final BrandMapper mapper;

    @Override
    public BrandResponseDto save(BrandRequestDto requestDto) {
        List<String>brandNamesFromDb = findAll().stream()
                .map(BrandResponseDto::getName)
                .toList();
        if (brandNamesFromDb.contains(requestDto.getName())) {
            throw new EntityNotUniqueException("Brand name should be unique, brand "
                    + requestDto.getName() + " already exist");
        }
        Brand brand = mapper.toModel(requestDto);
        Brand brandFromDb = brandRepository.save(brand);
        return mapper.toDto(brandFromDb);
    }

    @Override
    public Optional<Brand> findById(Long id) {
        return brandRepository.findById(id);
    }

    @Override
    public List<BrandResponseDto> findAll() {
        return brandRepository.findAll();
    }

    @Override
    public void update(Long id, Brand brand) {
        Brand brandFromDb =
                brandRepository.findById(id).orElseThrow(
                        () -> new EntityNotFoundException("Can't find brand by id" + id));
        brandFromDb.setName(brand.getName());
        brandFromDb.setDescription(brand.getDescription());
        brandFromDb.setDescription(brand.getDescription());
        brandRepository.save(brandFromDb);
    }

    @Override
    public void deleteById(Long id) {

    }
}
