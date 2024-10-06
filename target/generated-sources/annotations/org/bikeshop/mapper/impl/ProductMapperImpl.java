package org.bikeshop.mapper.impl;

import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.bikeshop.dto.request.CreateProductRequestDto;
import org.bikeshop.dto.response.product.ProductResponseDto;
import org.bikeshop.mapper.ProductMapper;
import org.bikeshop.model.Brand;
import org.bikeshop.model.Category;
import org.bikeshop.model.Currency;
import org.bikeshop.model.Product;
import org.bikeshop.model.ProductImages;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-06T15:16:03+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.11 (Oracle Corporation)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public ProductResponseDto toDto(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductResponseDto productResponseDto = new ProductResponseDto();

        Long id = productCurrencyId( product );
        if ( id != null ) {
            productResponseDto.setCurrencyId( id );
        }
        Long id1 = productBrandId( product );
        if ( id1 != null ) {
            productResponseDto.setBrandId( id1 );
        }
        Long id2 = productCategoryId( product );
        if ( id2 != null ) {
            productResponseDto.setCategoryId( id2 );
        }
        if ( product.getId() != null ) {
            productResponseDto.setId( product.getId() );
        }
        if ( product.getProductCode() != null ) {
            productResponseDto.setProductCode( product.getProductCode() );
        }
        productResponseDto.setQuantity( product.getQuantity() );
        if ( product.getTitle() != null ) {
            productResponseDto.setTitle( product.getTitle() );
        }
        if ( product.getDescription() != null ) {
            productResponseDto.setDescription( product.getDescription() );
        }
        if ( product.getPriceInCurrency() != null ) {
            productResponseDto.setPriceInCurrency( product.getPriceInCurrency() );
        }
        if ( product.getPriceUah() != null ) {
            productResponseDto.setPriceUah( product.getPriceUah() );
        }
        if ( product.getWholesalePrice() != null ) {
            productResponseDto.setWholesalePrice( product.getWholesalePrice() );
        }
        if ( product.getSelfCost() != null ) {
            productResponseDto.setSelfCost( product.getSelfCost() );
        }
        Set<ProductImages> set = product.getImages();
        if ( set != null ) {
            productResponseDto.setImages( new LinkedHashSet<ProductImages>( set ) );
        }

        return productResponseDto;
    }

    @Override
    public Product toModel(CreateProductRequestDto requestDto) {
        if ( requestDto == null ) {
            return null;
        }

        Product product = new Product();

        if ( requestDto != null ) {
            product.setCurrency( createProductRequestDtoToCurrency( requestDto ) );
        }
        if ( requestDto != null ) {
            product.setBrand( createProductRequestDtoToBrand( requestDto ) );
        }
        if ( requestDto != null ) {
            product.setCategory( createProductRequestDtoToCategory( requestDto ) );
        }
        if ( requestDto.getProductCode() != null ) {
            product.setProductCode( requestDto.getProductCode() );
        }
        product.setQuantity( requestDto.getQuantity() );
        if ( requestDto.getTitle() != null ) {
            product.setTitle( requestDto.getTitle() );
        }
        if ( requestDto.getDescription() != null ) {
            product.setDescription( requestDto.getDescription() );
        }
        if ( requestDto.getPriceInCurrency() != null ) {
            product.setPriceInCurrency( requestDto.getPriceInCurrency() );
        }
        if ( requestDto.getWholesalePrice() != null ) {
            product.setWholesalePrice( requestDto.getWholesalePrice() );
        }
        if ( requestDto.getSelfCost() != null ) {
            product.setSelfCost( requestDto.getSelfCost() );
        }
        Set<ProductImages> set = requestDto.getImages();
        if ( set != null ) {
            product.setImages( new LinkedHashSet<ProductImages>( set ) );
        }
        product.setDeleted( requestDto.isDeleted() );
        product.setEnabled( requestDto.isEnabled() );

        return product;
    }

    private Long productCurrencyId(Product product) {
        if ( product == null ) {
            return null;
        }
        Currency currency = product.getCurrency();
        if ( currency == null ) {
            return null;
        }
        Long id = currency.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long productBrandId(Product product) {
        if ( product == null ) {
            return null;
        }
        Brand brand = product.getBrand();
        if ( brand == null ) {
            return null;
        }
        Long id = brand.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long productCategoryId(Product product) {
        if ( product == null ) {
            return null;
        }
        Category category = product.getCategory();
        if ( category == null ) {
            return null;
        }
        Long id = category.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected Currency createProductRequestDtoToCurrency(CreateProductRequestDto createProductRequestDto) {
        if ( createProductRequestDto == null ) {
            return null;
        }

        Currency currency = new Currency();

        if ( createProductRequestDto.getCurrencyId() != null ) {
            currency.setId( createProductRequestDto.getCurrencyId() );
        }

        return currency;
    }

    protected Brand createProductRequestDtoToBrand(CreateProductRequestDto createProductRequestDto) {
        if ( createProductRequestDto == null ) {
            return null;
        }

        Brand brand = new Brand();

        if ( createProductRequestDto.getBrandId() != null ) {
            brand.setId( createProductRequestDto.getBrandId() );
        }

        return brand;
    }

    protected Category createProductRequestDtoToCategory(CreateProductRequestDto createProductRequestDto) {
        if ( createProductRequestDto == null ) {
            return null;
        }

        Category category = new Category();

        if ( createProductRequestDto.getCategoryId() != null ) {
            category.setId( createProductRequestDto.getCategoryId() );
        }

        return category;
    }
}
