package org.bikeshop.dto;

public record ProductSearchParameters(Long[] brandsIds, Long[] categoriesIds) {
}
