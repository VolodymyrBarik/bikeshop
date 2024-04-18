package org.bikeshop.service.impls;

import lombok.RequiredArgsConstructor;
import org.bikeshop.dto.request.CreateCategoryRequestDto;
import org.bikeshop.dto.response.CategoryResponseDto;
import org.bikeshop.mapper.CategoryMapper;
import org.bikeshop.model.Category;
import org.bikeshop.repository.CategoryRepository;
import org.bikeshop.service.CategoryService;
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
}
