package org.bikeshop.repository.product;

import java.util.Arrays;
import org.bikeshop.model.Product;
import org.bikeshop.repository.SpecificationProvider;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class CategorySpecificationProvider implements SpecificationProvider<Product> {
    @Override
    public String getKey() {
        return "category";
    }

    @Override
    public Specification<Product> getSpecification(Long[] params) {
        return (root, query, criteriaBuilder) ->
                root.get("category").get("id").in(Arrays.stream(params).toArray());
    }
}
