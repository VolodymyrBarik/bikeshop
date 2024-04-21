package org.bikeshop.service.impls;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.function.Predicate;
import lombok.RequiredArgsConstructor;
import org.bikeshop.dto.request.CreateBrandRequestDto;
import org.bikeshop.dto.response.BrandResponseDto;
import org.bikeshop.mapper.BrandMapper;
import org.bikeshop.model.Brand;
import org.bikeshop.repository.BrandRepository;
import org.bikeshop.service.BrandService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {
    private final BrandRepository brandRepository;
    private final BrandMapper mapper;

    @Override
    public BrandResponseDto save(CreateBrandRequestDto requestDto) {
        List<String> brandNamesFromDb = findAllEnabledNonDisabled().stream()
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
    public BrandResponseDto findById(Long id) {
        Brand brand = brandRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Can't find category by id " + id));
        return mapper.toDto(brand);
    }

    @Override
    public List<BrandResponseDto> findAllEnabledNonDisabled() {
        return brandRepository.findAll().stream()
                .filter(Brand::isEnabled)
                .filter(Predicate.not(Brand::isDeleted))
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public List<BrandResponseDto> findAll() {
        List<Brand> allFromDb = brandRepository.findAll();
        return allFromDb.stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public BrandResponseDto update(Long id, CreateBrandRequestDto requestDto) {
        Brand brandFromDb =
                brandRepository.findById(id).orElseThrow(
                        () -> new EntityNotFoundException("Can't find brand by id" + id));
        brandFromDb.setName(requestDto.getName());
        brandFromDb.setDescription(requestDto.getDescription());
        brandFromDb.setDescription(requestDto.getDescription());
        Brand savedBrand = brandRepository.save(brandFromDb);
        return mapper.toDto(savedBrand);
    }

    @Override
    public void enableBrand(Long id) {
        Brand brandFromDb = brandRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Can't find brand by id" + id));
        if (brandFromDb.isDeleted()) {
            throw new RuntimeException("Can't enable deleted brand");
        } else {
            brandFromDb.setEnabled(true);
            brandRepository.save(brandFromDb);
        }
    }

    @Override
    public void disableBrand(Long id) {
        Brand brandFromDb = brandRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Can't find brand by id" + id));
        brandFromDb.setEnabled(false);
        brandRepository.save(brandFromDb);
    }

    @Override
    public void deleteById(Long id) {
        Brand brandFromDb = brandRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Can't find brand by id" + id));
        if (brandFromDb.isEnabled()) {
            throw new RuntimeException("Can't delete enabled brand");
        } else {
            brandFromDb.setDeleted(true);
            brandRepository.save(brandFromDb);
        }
    }

    @Override
    public void undeleteById(Long id) {
        Brand brandFromDb = brandRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Can't find brand by id" + id));
        brandFromDb.setDeleted(false);
        brandRepository.save(brandFromDb);
    }
}
