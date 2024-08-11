package org.bikeshop.service.impls;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;

import lombok.RequiredArgsConstructor;
import org.bikeshop.dto.ProductSearchParameters;
import org.bikeshop.dto.request.CreateProductRequestDto;
import org.bikeshop.dto.response.product.ProductResponseDto;
import org.bikeshop.exception.EntityNotFoundException;
import org.bikeshop.mapper.ProductMapper;
import org.bikeshop.model.Brand;
import org.bikeshop.model.Category;
import org.bikeshop.model.Currency;
import org.bikeshop.model.Product;
import org.bikeshop.repository.BrandRepository;
import org.bikeshop.repository.CategoryRepository;
import org.bikeshop.repository.CurrencyRepository;
import org.bikeshop.repository.product.ProductRepository;
import org.bikeshop.repository.product.ProductSpecificationBuilder;
import org.bikeshop.service.ProductService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private static final double WHOLESALE_MULTIPLIER = 0.68;
    private final ProductMapper mapper;
    private final ProductRepository productRepository;
    private final ProductSpecificationBuilder productSpecificationBuilder;
    private final CurrencyRepository currencyRepository;
    private final CategoryRepository categoryRepository;
    private final BrandRepository brandRepository;

    @Override
    public ProductResponseDto save(CreateProductRequestDto requestDto) {
        Product product = mapper.toModel(requestDto);
        Brand brandFromDb = getBrand(requestDto.getBrandId());
        product.setBrand(brandFromDb);
        Currency currencyFromDb = getCurrency(requestDto.getCurrencyId());
        product.setCurrency(currencyFromDb);
        Category categoryFromDb = getCategory(requestDto.getCategoryId());
        product.setCategory(categoryFromDb);
        product.setPriceInCurrency(requestDto.getPriceInCurrency());
        BigDecimal priceUah = getPriceUah(product);
        product.setPriceUah(priceUah);
        BigDecimal wholesalePrice = getWholesalePrice(product);
        product.setWholesalePrice(wholesalePrice);
        Product productFromDb = productRepository.save(product);
        return mapper.toDto(productFromDb);
    }

    @Override
    public List<ProductResponseDto> findAllIncludingDisabled(Pageable pageable) {
        return productRepository.findAll(pageable).stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public List<ProductResponseDto> findAllEnabled(Pageable pageable) {
        return productRepository.findAll(pageable).stream()
                .filter(Product::isEnabled)
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public ProductResponseDto findById(Long id) {
        Product productFromDb = findProductById(id);
        return mapper.toDto(productFromDb);
    }

    @Override
    public List<Product> findAllByCurrencyId(Long currencyId) {
        return productRepository.findAllByCurrencyId(currencyId);
    }

    @Override
    public void update(Long id, CreateProductRequestDto dto) {
        Product productFromDb = findProductById(id);
        productFromDb.setProductCode(dto.getProductCode());
        productFromDb.setQuantity(dto.getQuantity());
        productFromDb.setTitle(dto.getTitle());
        Brand brandFromDb = getBrand(dto.getBrandId());
        productFromDb.setBrand(brandFromDb);
        productFromDb.setDescription(dto.getDescription());
        productFromDb.setPriceInCurrency(dto.getPriceInCurrency());
        Currency currencyFromDb = getCurrency(dto.getCurrencyId());
        productFromDb.setCurrency(currencyFromDb);
        productFromDb.setSelfCost(dto.getSelfCost());
        Category categoryFromDb = getCategory(dto.getCategoryId());
        productFromDb.setCategory(categoryFromDb);
        productFromDb.setImages(dto.getImages());
        BigDecimal priceUah = getPriceUah(productFromDb);
        productFromDb.setPriceUah(priceUah);
        BigDecimal wholesalePrice = getWholesalePrice(productFromDb);
        productFromDb.setWholesalePrice(wholesalePrice);
        productRepository.save(productFromDb);
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<ProductResponseDto> search(
            ProductSearchParameters searchParameters, Pageable pageable) {
        Specification<Product> productSpecification =
                productSpecificationBuilder.build(searchParameters);
        return productRepository.findAllActive(productSpecification, pageable).stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public void updatePriceByCurrency(Long currencyId) {
        productRepository.findAllByCurrencyId(currencyId).stream()
                .peek(product -> {
                    product.setPriceUah(getPriceUah(product));
                    product.setWholesalePrice(getWholesalePrice(product));
                }).forEach(productRepository::save);
    }

    @Override
    public void enable(Long id) {
        Product productFromDb = findProductById(id);
        productFromDb.setEnabled(true);
        productRepository.save(productFromDb);
    }

    @Override
    public void disable(Long id) {
        Product productFromDb = findProductById(id);
        productFromDb.setEnabled(false);
        productRepository.save(productFromDb);
    }

    @Override
    public void undelete(Long id) {
        Product productFromDb = findProductById(id);
        productFromDb.setDeleted(false);
        productRepository.save(productFromDb);
    }

    private Currency getCurrency(Long id) {
        return currencyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Can't find currency by id " + id));
    }

    private Category getCategory(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Can't find currency by id " + id));
    }

    private Brand getBrand(Long id) {
        return brandRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Can't find brand by id " + id));
    }

    private BigDecimal getWholesalePrice(Product product) {
        BigDecimal priceInCurrency = product.getPriceInCurrency();
        Currency currency = currencyRepository.findById(product.getCurrency().getId())
                .orElseThrow(() -> new NoSuchElementException(
                        "Can't find currency by id " + product.getCurrency().getId()));
        return priceInCurrency.multiply(currency.getExchangeRate()).multiply(
                BigDecimal.valueOf(WHOLESALE_MULTIPLIER));
    }

    private BigDecimal getPriceUah(Product product) {
        BigDecimal priceInCurrency = product.getPriceInCurrency();
        Currency currency = currencyRepository.findById(product.getCurrency().getId())
                .orElseThrow(() -> new NoSuchElementException(
                        "Can't find currency by id " + product.getCurrency().getId()));
        return priceInCurrency.multiply(currency.getExchangeRate());
    }

    private Product findProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Can't find product by id " + id));
    }
}
