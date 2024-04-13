package org.bikeshop.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import java.math.BigDecimal;
import lombok.AccessLevel;
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

    @ManyToOne(fetch = FetchType.LAZY)
    private Currency currency;

    @Setter(AccessLevel.NONE)
    @Min(value = 0, message = "Product price in hryvna can't be less than 0")
    private BigDecimal priceUah;

    @Setter(AccessLevel.NONE)
    @Min(value = 0, message = "Product wholesale price in hryvna can't be less than 0")
    private BigDecimal wholesalePrice;

    @Min(value = 0, message = "Product self cost price in hryvna can't be less than 0")
    private BigDecimal selfCost;

//    private int wholesaleAdditionalDiscountInPercent = 0;
//    private int retailDiscountInPercent = 0;

    @ManyToOne(fetch = FetchType.LAZY)
    private Brand brand;
    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;
    private String images;
    private boolean isDeleted = false;
    private boolean enabled = false;

    private void updateWholesalePrice() {
        if (priceUah != null) {
            this.wholesalePrice = priceUah.multiply(BigDecimal.valueOf(0.68));
        }
    }

    private void updatePriceUah() {
        if (priceInCurrency != null && currency != null) {
            this.priceUah =
                    priceInCurrency.multiply(BigDecimal.valueOf(currency.getExchangeRate()));
        }
        updateWholesalePrice();
    }

    public void setPriceInCurrency(BigDecimal priceInCurrency) {
        this.priceInCurrency = priceInCurrency;
        updatePriceUah();
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
        updatePriceUah();
    }
}
