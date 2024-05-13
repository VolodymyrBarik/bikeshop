package org.bikeshop.repository.product;

import java.util.Arrays;
import org.bikeshop.model.Product;
import org.bikeshop.repository.SpecificationProvider;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class BrandSpecificationProvider implements SpecificationProvider<Product> {

    @Override
    public String getKey() {
        return "brand";
    }

    public Specification<Product> getSpecification(Long[] params) {
        return (root, query, criteriaBuilder) ->
                root.get("brand").get("id").in(Arrays.stream(params).toArray());
    }
}
