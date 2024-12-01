package org.bikeshop.repository.order;

import lombok.RequiredArgsConstructor;
import org.bikeshop.dto.OrderSearchParameters;
import org.bikeshop.model.Order;
import org.bikeshop.repository.SpecificationBuilder;
import org.bikeshop.repository.SpecificationProviderManager;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class OrderSpecificationBuilder implements SpecificationBuilder<Order, OrderSearchParameters> {

    private final SpecificationProviderManager<Order> orderSpecificationProviderManager;

    @Override
    public Specification<Order> build(OrderSearchParameters searchParameters) {
        Specification<Order> spec = Specification.where(null);
        if (searchParameters.statusesIds() != null && searchParameters.statusesIds().length > 0) {
            spec = spec.and(orderSpecificationProviderManager.getSpecificationProvider(
                    "status").getSpecification(searchParameters.statusesIds()));
        }
        return spec;
    }
}
