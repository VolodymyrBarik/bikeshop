package org.bikeshop.repository;

import java.util.List;
import org.bikeshop.model.Brand;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BrandRepository extends JpaRepository<Brand, Long> {
    @NonNull
    List<Brand> findAll();
}
