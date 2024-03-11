package org.bikeshop.service;

import java.util.List;
import java.util.Optional;
import jakarta.persistence.EntityNotFoundException;
import org.bikeshop.model.Brand;
import org.bikeshop.repository.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {
    private final BrandRepository brandRepository;

    @Override
    public void save(Brand brand) {
        brandRepository.save(brand);
    }

    @Override
    public Optional<Brand> findById(Long id) {
        return brandRepository.findById(id);
    }

    @Override
    public List<Brand> findAll() {
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
    }
}
