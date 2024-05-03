package org.bikeshop.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.bikeshop.dto.request.CreateCategoryRequestDto;
import org.bikeshop.dto.response.CategoryResponseDto;
import org.bikeshop.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @Operation(summary = "Create a new category", description = "Create a new category")
    @PostMapping
    public CategoryResponseDto create(@RequestBody @Valid CreateCategoryRequestDto requestDto) {
        return categoryService.save(requestDto);
    }

    //@PreAuthorize("isAuthenticated() and principal.enabled")
    @Operation(summary = "Get all enabled, non deleted categories", description = "Get a list of all enabled, non deleted categories")
    @GetMapping
    public List<CategoryResponseDto> getAllEnabledNonDeleted() {
        return categoryService.findAllEnabledNonDeleted();
    }

    @Operation(summary = "Get all categories", description = "Get a list of all categories including disabled and deleted")
    @GetMapping("/all")
    public List<CategoryResponseDto> getAll() {
        return categoryService.findAll();
    }

    @Operation(summary = "Get a category", description = "Returns a category by it's id")
    @GetMapping("/{id}")
    public CategoryResponseDto getBookById(@PathVariable Long id) {
        return categoryService.findById(id);
    }

    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "Update a category", description = "Updates a category by it's id")
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public void update(@PathVariable Long id,
                       @RequestBody
                       @Valid CreateCategoryRequestDto requestDto) {
        categoryService.update(id, requestDto);
    }

    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "Enable a category", description = "Enables a category by it's id")
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{id}/enable")
    public void enableCategory(@PathVariable Long id) {
        categoryService.enableCategory(id);
    }

    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "Disable a category", description = "Disables a category by it's id")
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{id}/disable")
    public void disableCategory(@PathVariable Long id) {
        categoryService.disableCategoryById(id);
    }

    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "Delete a category", description = "Deletes a category by it's id")
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        categoryService.deleteById(id);
    }

    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "Undelete a category", description = "Undeletes a category by it's id")
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{id}/undelete")
    public void undelete(@PathVariable Long id) {
        categoryService.undeleteById(id);
    }
}
