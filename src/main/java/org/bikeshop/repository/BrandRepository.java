package org.bikeshop.repository;

import java.util.List;
import lombok.NonNull;
import org.bikeshop.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand, Long> {
    @NonNull
    List<Brand> findAll();
}
