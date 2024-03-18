package org.bikeshop.service;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.bikeshop.dto.request.BrandRequestDto;
import org.bikeshop.dto.response.BrandResponseDto;
import org.bikeshop.mapper.BrandMapper;
import org.bikeshop.model.Brand;
import org.bikeshop.repository.BrandRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {
    private final BrandRepository brandRepository;
    private final BrandMapper mapper;

    @Override
    public BrandResponseDto save(BrandRequestDto requestDto) {
        List<String> brandNamesFromDb = findAll().stream()
                .map(BrandResponseDto::getName)
                .toList();
        if (brandNamesFromDb.contains(requestDto.getName())) {
            throw new EntityNotFoundException("Brand name should be unique, brand "
                    + requestDto.getName() + " already exist");
        }
        Brand brand = mapper.toModel(requestDto);
        Brand brandFromDb = brandRepository.save(brand);
        return mapper.toDto(brandFromDb);
    }

    @Override
    public BrandResponseDto getById(Long id) {
        Brand brand = brandRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Can't find category by id " + id));
        return mapper.toDto(brand);
    }

    @Override
    public List<BrandResponseDto> findAll() {
        List<Brand> allFromDb = brandRepository.findAll();
        return allFromDb.stream().map(mapper::toDto)
                .toList();
    }

    @Override
    public BrandResponseDto update(Long id, Brand brand) {
        Brand brandFromDb =
                brandRepository.findById(id).orElseThrow(
                        () -> new EntityNotFoundException("Can't find brand by id" + id));
        brandFromDb.setName(brand.getName());
        brandFromDb.setDescription(brand.getDescription());
        brandFromDb.setDescription(brand.getDescription());
        Brand savedBrand = brandRepository.save(brandFromDb);
        return mapper.toDto(savedBrand);
    }

    @Override
    public void deleteById(Long id) {

    }
}
