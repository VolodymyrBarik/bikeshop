package org.bikeshop.service.impls;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.bikeshop.dto.ProductSearchParameters;
import org.bikeshop.dto.request.CreateProductRequestDto;
import org.bikeshop.dto.response.BrandResponseDto;
import org.bikeshop.dto.response.ProductResponseDto;
import org.bikeshop.exception.EntityNotFoundException;
import org.bikeshop.mapper.BrandMapper;
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
import org.bikeshop.service.BrandService;
import org.bikeshop.service.CategoryService;
import org.bikeshop.service.CurrencyService;
import org.bikeshop.service.ProductService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductMapper mapper;
    private final ProductRepository productRepository;
    private final ProductSpecificationBuilder productSpecificationBuilder;
    private final CurrencyRepository currencyRepository;
    private final CategoryRepository categoryRepository;
    private final CurrencyService currencyService;
    private final BrandService brandService;
    private final BrandRepository brandRepository;
    private final CategoryService categoryService;
    private final BrandMapper brandMapper;


    @Override
    public ProductResponseDto save(CreateProductRequestDto requestDto) {
        Product product = mapper.toModel(requestDto);
        Product productFromDb = productRepository.save(product);
        return mapper.toDto(productFromDb);
    }

    @Override
    public List<ProductResponseDto> findAll(Pageable pageable) {
        return productRepository.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public ProductResponseDto findById(Long id) {
        Product productFromDb = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Can't find product by id " + id));
        return mapper.toDto(productFromDb);
    }

    @Override
    public void update(Long id, CreateProductRequestDto dto) {
        Product productFromDb = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Can't find product by id " + id));
        productFromDb.setProductCode(dto.getProductCode());
        productFromDb.setQuantity(dto.getQuantity());
        productFromDb.setTitle(dto.getTitle());
        Brand brandFromDb = brandRepository.findById(dto.getBrandId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Can't find brand by id " + dto.getBrandId()));
        productFromDb.setBrand(brandFromDb);
        productFromDb.setDescription(dto.getDescription());
        productFromDb.setPriceInCurrency(dto.getPriceInCurrency());
        Currency currencyFromDb = currencyRepository.findById(dto.getCurrencyId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Can't find currency by id " + dto.getCurrencyId()));
        productFromDb.setCurrency(currencyFromDb);
        productFromDb.setSelfCost(dto.getSelfCost());
        Category categoryFromDb = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Can't find category by id " + dto.getCategoryId()));
        productFromDb.setCategory(categoryFromDb);
        productFromDb.setImages(dto.getImages());
        productRepository.save(productFromDb);
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<ProductResponseDto> search(ProductSearchParameters searchParameters,
                                           Pageable pageable) {

        Specification<Product> productSpecification
                = productSpecificationBuilder.build(searchParameters);
        return productRepository.findAll(productSpecification, pageable).stream()
                .map(p -> productRepository.findById(p.getId()).orElse(new Product()))
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public List<ProductResponseDto> getAllProductsByCategoryId(Long categoryId) {
        return List.of();
    }

    @Override
    public List<ProductResponseDto> getAllProductsByCategoryIdAndBrands(Long[] categoryId,
                                                                        Long[] brandsIds) {
        return List.of();
    }
}
