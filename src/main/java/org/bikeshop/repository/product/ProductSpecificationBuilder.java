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
        Specification<Product> spec = Specification.where(null);
        if (searchParameters.brands() != null && searchParameters.brands().length > 0) {
            spec = spec.and(productSpecificationProviderManager.getSpecificationProvider(
                    "brand").getSpecification(searchParameters.brands()));
        }
        if (searchParameters.categories() != null && searchParameters.categories().length > 0) {
            spec = spec.and(productSpecificationProviderManager.getSpecificationProvider(
                    "category").getSpecification(searchParameters.categories()));
        }
        return spec;
    }
}
