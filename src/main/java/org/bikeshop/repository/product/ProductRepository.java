package org.bikeshop.repository.product;

import java.util.List;
import java.util.Optional;
import org.bikeshop.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;

public interface ProductRepository extends JpaRepository<Product, Long>,
        JpaSpecificationExecutor<Product> {

    @NonNull
    @Query("SELECT DISTINCT p FROM Product p LEFT JOIN FETCH p.images "
            + "WHERE p.deleted = false")
    Page<Product> findAll(@NonNull Pageable pageable);

    @Query("SELECT p FROM Product p LEFT JOIN FETCH p.images "
            + "WHERE p.deleted = false AND p.enabled AND p.id = :id")
    Optional<Product> findByIdWithImages(Long id);

    @NonNull
    @Override
    //@Query("SELECT p FROM Product p WHERE p.deleted = false AND p.enabled AND p.id = :id")
    Optional<Product> findById(@NonNull Long id);

    List<Product> findAllByCurrencyId(Long currencyId);

    default Page<Product> findAllActive(Specification<Product> spec, Pageable pageable) {
        Specification<Product> activeSpec =
                Specification.<Product>where((root, query, criteriaBuilder) ->
                        criteriaBuilder.and(
                                criteriaBuilder.isFalse(root.get("deleted")),
                                criteriaBuilder.isTrue(root.get("enabled"))
                        )
                ).and(spec);
        return findAll(activeSpec, pageable);
    }
}
