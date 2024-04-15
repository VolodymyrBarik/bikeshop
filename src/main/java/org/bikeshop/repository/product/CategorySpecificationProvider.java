package org.bikeshop.repository.product;

import java.util.Arrays;
import org.bikeshop.model.Product;
import org.bikeshop.repository.SpecificationProvider;
import org.springframework.data.jpa.domain.Specification;

public class CategorySpecificationProvider implements SpecificationProvider<Product> {
    @Override
    public String getKey() {
        return "category";
    }

    @Override
    public Specification<Product> getSpecification(String[] params) {
        return (root, query, criteriaBuilder) ->
                root.get("category").in(Arrays.stream(params).toArray());
    }
}
