package org.bikeshop.service.impls;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.bikeshop.dto.ProductSearchParameters;
import org.bikeshop.dto.request.CreateProductRequestDto;
import org.bikeshop.dto.response.ProductResponseDto;
import org.bikeshop.exception.EntityNotFoundException;
import org.bikeshop.mapper.ProductMapper;
import org.bikeshop.model.Product;
import org.bikeshop.repository.ProductRepository;
import org.bikeshop.service.ProductService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductMapper mapper;
    private final ProductRepository productRepository;

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
        productFromDb.setBrand(dto.getBrand());
        productFromDb.setDescription(dto.getDescription());
        productFromDb.setPriceInCurrency(dto.getPriceInCurrency());
        productFromDb.setCurrency(dto.getCurrency());
        productFromDb.setSelfCost(dto.getSelfCost());
        productFromDb.setBrand(dto.getBrand());
        productFromDb.setCategory(dto.getCategory());



    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<ProductResponseDto> search(ProductSearchParameters searchParameters,
                                           Pageable pageable) {
        return List.of();
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
