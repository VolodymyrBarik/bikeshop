package org.bikeshop.repository.product;

import lombok.RequiredArgsConstructor;
import org.bikeshop.dto.ProductSearchParameters;
import org.bikeshop.model.Product;
import org.bikeshop.repository.SpecificationBuilder;
import org.bikeshop.repository.SpecificationProviderManager;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ProductSpecificationBuilder implements SpecificationBuilder<Product> {
    private final SpecificationProviderManager<Product> productSpecificationProviderManager;

    @Override
    public Specification<Product> build(ProductSearchParameters searchParameters) {
        return null;
    }
}
