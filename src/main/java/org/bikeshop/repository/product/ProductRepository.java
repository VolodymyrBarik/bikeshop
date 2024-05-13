package org.bikeshop.repository.product;

import java.util.Optional;
import org.bikeshop.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;


public interface ProductRepository extends JpaRepository<Product, Long>,
        JpaSpecificationExecutor<Product> {

    @EntityGraph(attributePaths = {"images"})
    @Override
    @NonNull
    Page<Product> findAll(@NonNull Specification<Product> spec, @NonNull Pageable pageable);


    @Query("SELECT p FROM Product p LEFT JOIN FETCH p.images WHERE p.isDeleted = false AND p.id = :id")
    Optional<Product> findByIdWithImages(Long id);

}
