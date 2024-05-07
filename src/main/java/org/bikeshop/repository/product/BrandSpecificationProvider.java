package org.bikeshop.repository.product;

import java.util.Arrays;
import org.bikeshop.model.Product;
import org.bikeshop.repository.SpecificationProvider;
import org.springframework.data.jpa.domain.Specification;

public class BrandSpecificationProvider implements SpecificationProvider<Product> {
    @Override
    public String getKey() {
        return "brand";
    }

    @Override
    public Specification<Product> getSpecification(String[] params) {
        return (root, query, criteriaBuilder) ->
                root.get("brand").in(Arrays.stream(params).toArray());
    }
}
