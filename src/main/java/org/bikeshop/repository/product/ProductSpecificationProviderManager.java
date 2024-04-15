package org.bikeshop.repository.product;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.bikeshop.model.Product;
import org.bikeshop.repository.SpecificationProvider;
import org.bikeshop.repository.SpecificationProviderManager;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ProductSpecificationProviderManager implements SpecificationProviderManager<Product> {
    private final List<SpecificationProvider<Product>> productSpecificationProviders;

    @Override
    public SpecificationProvider<Product> getSpecificationProvider(String key) {
        return productSpecificationProviders.stream()
                .filter(p -> p.getKey().equals(key))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(
                        "Can't find specification provider for key " + key));
    }
}
