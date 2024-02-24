package org.bikeshop.model;

import java.math.BigDecimal;
import java.util.Set;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.Min;

@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String productCode;
    @Min(value = 0, message = "Product quantity can't be less than 0")
    private int quantity;
    private String title;
    private String description;
    private BigDecimal price;
    private BigDecimal wholesalePrice;
    private Currency currency;
    private Brand brand;
    private Set<Category> categories;
    private Set<String> images;
    private boolean isDeleted = false;
}
