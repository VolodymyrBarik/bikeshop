package org.bikeshop.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import java.math.BigDecimal;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;

@Entity
@Getter
@Setter
@Table(name = "products")
@SQLDelete(sql = "UPDATE products SET is_deleted=true WHERE id=?")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String productCode;

    @Min(value = 0, message = "Product quantity can't be less than 0")
    private int quantity;
    private String title;
    private String description;

    @Min(value = 0, message = "Product currency price can't be less than 0")
    private BigDecimal priceInCurrency;

    @Min(value = 0, message = "Product price in hryvna can't be less than 0")
    private BigDecimal priceUah;

    @Min(value = 0, message = "Product wholesale price in hryvna can't be less than 0")
    private BigDecimal wholesalePrice;

    @Min(value = 0, message = "Product selfcost price in hryvna can't be less than 0")
    private BigDecimal selfCost;

    private int wholesaleAdditionalDiscountInPercent = 0;
    private int retailDiscountInPercent = 0;
    @ManyToOne
    private Currency currency;
    @ManyToOne
    private Brand brand;
    @ManyToMany
    private Set<Category> categories;
    private String images;
    private boolean isDeleted = false;
    private boolean enabled = false;
}
