package org.bikeshop.dto.response.product;

import java.math.BigDecimal;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;
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
    private Long currencyId;
    private Long brandId;
    private Long categoryId;
    private Set<ProductImages>images;
}
