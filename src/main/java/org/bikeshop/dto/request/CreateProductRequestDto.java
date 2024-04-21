package org.bikeshop.dto.request;

import jakarta.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import org.bikeshop.model.ProductImages;

@Getter
@Setter
public class CreateProductRequestDto {
    @NotEmpty
    private String productCode;
    @NotEmpty
    private String title;
    private String description;
    private int quantity;
    private BigDecimal priceInCurrency;
    private BigDecimal wholesalePrice;
    private BigDecimal selfCost;
    private int wholesaleAdditionalDiscountInPercent = 0;
    private int retailDiscountInPercent = 0;
    //@NotEmpty
    private Long currencyId;
    //@NotEmpty
    private Long brandId;
    //@NotEmpty
    private Long categoryId;
    private Set<ProductImages> images;
    private boolean isDeleted = false;
    private boolean enabled = false;
}
