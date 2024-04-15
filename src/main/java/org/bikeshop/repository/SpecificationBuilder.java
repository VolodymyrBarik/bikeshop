package org.bikeshop.repository;

import org.bikeshop.dto.ProductSearchParameters;
import org.springframework.data.jpa.domain.Specification;

public interface SpecificationBuilder<T> {
    Specification<T> build(ProductSearchParameters searchParameters);
}

