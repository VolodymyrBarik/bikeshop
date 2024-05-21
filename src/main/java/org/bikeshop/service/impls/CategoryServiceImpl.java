package org.bikeshop.service.impls;

import java.util.List;
import java.util.function.Predicate;
import lombok.RequiredArgsConstructor;
import org.bikeshop.dto.request.CreateCategoryRequestDto;
import org.bikeshop.dto.response.CategoryResponseDto;
import org.bikeshop.exception.EntityNotFoundException;
import org.bikeshop.mapper.CategoryMapper;
import org.bikeshop.model.Category;
import org.bikeshop.repository.CategoryRepository;
import org.bikeshop.service.CategoryService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper mapper;

    @Override
    public CategoryResponseDto save(CreateCategoryRequestDto requestDto) {
        Category category = mapper.toModel(requestDto);
        Category categoryFromDb = categoryRepository.save(category);
        return mapper.toDto(categoryFromDb);
    }

    @Override
    public CategoryResponseDto findById(Long id) {
        Category categoryFromDb = categoryRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Can't find category by id " + id));
        return mapper.toDto(categoryFromDb);
    }

    @Override
    public List<CategoryResponseDto> findAllEnabledNonDeleted(Pageable pageable) {
        return categoryRepository.findAll().stream()
                .filter(Category::isEnabled)
                .filter(Predicate.not(Category::isDeleted))
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public List<CategoryResponseDto> findAll(Pageable pageable) {
        return categoryRepository.findAll().stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public CategoryResponseDto update(Long id, CreateCategoryRequestDto requestDto) {
        Category categoryFromDb = categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Can't find category by id " + id));
        categoryFromDb.setName(requestDto.getName());
        categoryFromDb.setDescription(requestDto.getDescription());
        categoryFromDb.setLogo(requestDto.getLogo());
        Category updatedCategory = categoryRepository.save(categoryFromDb);
        return mapper.toDto(updatedCategory);
    }

    @Override
    public void enableCategory(Long id) {
        Category categoryFromDb
                = categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Can't find category by id " + id));
        if (categoryFromDb.isDeleted()) {
            throw new RuntimeException("Can't enable deleted category");
        } else {
            categoryFromDb.setEnabled(true);
            categoryRepository.save(categoryFromDb);
        }
    }

    @Override
    public void disableCategoryById(Long id) {
        Category categoryFromDb
                = categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Can't find category by id " + id));
        categoryFromDb.setEnabled(false);
        categoryRepository.save(categoryFromDb);
    }

    @Override
    public void deleteById(Long id) {
        Category categoryFromDb
                = categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Can't find category by id " + id));
        if (categoryFromDb.isEnabled()) {
            throw new RuntimeException("Can't delete enabled category");
        } else {
            categoryFromDb.setDeleted(true);
            categoryRepository.save(categoryFromDb);
        }
    }

    @Override
    public void undeleteById(Long id) {
        Category categoryFromDb
                = categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Can't find category by id " + id));
        categoryFromDb.setDeleted(false);
        categoryRepository.save(categoryFromDb);
    }
}
