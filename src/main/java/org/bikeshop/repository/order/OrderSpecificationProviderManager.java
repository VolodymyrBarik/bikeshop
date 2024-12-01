package org.bikeshop.repository.order;


import java.util.List;
import lombok.RequiredArgsConstructor;
import org.bikeshop.model.Order;
import org.bikeshop.repository.SpecificationProvider;
import org.bikeshop.repository.SpecificationProviderManager;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class OrderSpecificationProviderManager implements SpecificationProviderManager<Order> {
    private final List<SpecificationProvider<Order>> orderSpecificationProviders;

    @Override
    public SpecificationProvider<Order> getSpecificationProvider(String key) {
        return orderSpecificationProviders.stream()
                .filter(p -> p.getKey().equals(key))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(
                        "Can't find specification provider for key " + key));
    }
}
