package org.bikeshop.dto.request;

import jakarta.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import org.bikeshop.model.Brand;
import org.bikeshop.model.Category;
import org.bikeshop.model.Currency;
import org.bikeshop.model.ProductImages;
import org.hibernate.validator.constraints.UniqueElements;

@Getter
@Setter
public class CreateProductRequestDto {
    @NotEmpty
    @UniqueElements
    private String productCode;
    @NotEmpty
    private String title;
    private String description;
    private int quantity;
    private BigDecimal priceInCurrency;
    private BigDecimal priceUah;
    private BigDecimal wholesalePrice;
    private BigDecimal selfCost;
    private int wholesaleAdditionalDiscountInPercent = 0;
    private int retailDiscountInPercent = 0;
    @NotEmpty
    private Currency currency;
    @NotEmpty
    private Brand brand;
    @NotEmpty
    private Category category;
    private Set<ProductImages> images;
    private boolean isDeleted = false;
    private boolean enabled = false;
}
