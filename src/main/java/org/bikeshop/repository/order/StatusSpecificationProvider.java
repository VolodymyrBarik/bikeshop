package org.bikeshop.repository.order;

import java.util.Arrays;
import org.bikeshop.model.Order;
import org.bikeshop.repository.SpecificationProvider;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class StatusSpecificationProvider implements SpecificationProvider<Order> {

    @Override
    public String getKey() {
        return "status";
    }

    public Specification<Order> getSpecification(Long[] params) {
        return (root, query, criteriaBuilder) ->
                root.get("currentStatus").get("id").in(Arrays.stream(params).toArray());
    }
}
