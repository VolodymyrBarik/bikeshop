package org.bikeshop.dto.response;

import java.math.BigDecimal;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import org.bikeshop.model.Brand;
import org.bikeshop.model.Category;
import org.bikeshop.model.Currency;
import org.bikeshop.model.ProductImages;

@Getter
@Setter
public class ProductResponseDto {
    private Long id;
    private String productCode;
    private int quantity;
    private String title;
    private String description;
    private BigDecimal priceInCurrency;
    private BigDecimal priceUah;
    private BigDecimal wholesalePrice;
    private BigDecimal selfCost;
    private final int wholesaleAdditionalDiscountInPercent = 0;
    private final int retailDiscountInPercent = 0;
    private Currency currency;
    private Brand brand;
    private Set<Category> categories;
    private Set<ProductImages>images;
    private final boolean isDeleted = false;
    private final boolean enabled = false;
}
