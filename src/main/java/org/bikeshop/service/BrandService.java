package org.bikeshop.service;

import java.util.List;
import java.util.Optional;
import org.bikeshop.model.Brand;

public interface BrandService {
    void save(Brand brand);

    Optional<Brand> findById(Long id);

    List<Brand> findAll();

    void update(Long id, Brand brand);
}
