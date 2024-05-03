package org.bikeshop.repository.product;

import java.util.List;
import java.util.Optional;
import org.bikeshop.model.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<Product, Long>,
        JpaSpecificationExecutor<Product> {
    //    @Query("SELECT p FROM Product p LEFT JOIN FETCH ProductImages pi WHERE pi.product.id = :id and p.isDeleted=false ")
//    Optional<Product> findByIdWithImages(Long id);

    @Query("SELECT DISTINCT p FROM Product p "
            + "LEFT JOIN FETCH p.images "
            + "LEFT JOIN FETCH p.brand "
            + "LEFT JOIN FETCH p.category "
            + "LEFT JOIN FETCH p.currency "
            + "WHERE p.isDeleted = false ")
    List<Product> findAllWithImagesBrandCategoryCurrency(Pageable pageable);

    @Query("SELECT p FROM Product p LEFT JOIN FETCH p.images WHERE p.isDeleted = false AND p.id = :id")
    Optional<Product> findByIdWithImages(Long id);
}
