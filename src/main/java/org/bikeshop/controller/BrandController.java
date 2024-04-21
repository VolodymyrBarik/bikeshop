package org.bikeshop.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.bikeshop.dto.request.CreateBrandRequestDto;
import org.bikeshop.dto.response.BrandResponseDto;
import org.bikeshop.service.BrandService;
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
@RequestMapping("/brands")
public class BrandController {
    private final BrandService brandService;

    //@PreAuthorize("isAuthenticated() and principal.enabled")
    @Operation(summary = "Get all enabled, non deleted brands", description = "Get a list of all enabled, non deleted brands")
    @GetMapping
    public List<BrandResponseDto> getAllEnabledNonDeleted() {
        return brandService.findAllEnabledNonDisabled();
    }

    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "Get all enabled", description = "Get a list of all brands")
    @GetMapping("/all")
    public List<BrandResponseDto> getAll() {
        return brandService.findAll();
    }

    @Operation(summary = "Get a brand", description = "Returns a brand by it's id")
    @GetMapping("/{id}")
    public BrandResponseDto getBookById(@PathVariable Long id) {
        return brandService.findById(id);
    }

    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "Create a new brand", description = "Create a new brand")
    @PostMapping
    public BrandResponseDto create(@RequestBody @Valid CreateBrandRequestDto requestDto) {
        return brandService.save(requestDto);
    }

    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "Update a brand", description = "Updates a brand by it's id")
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public void update(@PathVariable Long id,
                       @RequestBody
                       @Valid CreateBrandRequestDto requestDto) {
        brandService.update(id, requestDto);
    }

    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "Enable a brand", description = "Enables a brand by it's id")
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{id}/enable")
    public void enableBrand(@PathVariable Long id) {
        brandService.enableBrand(id);
    }

    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "Disable a brand", description = "Disables a brand by it's id")
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{id}/disable")
    public void disableBrand(@PathVariable Long id) {
        brandService.disableBrand(id);
    }

    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "Delete a brand", description = "Deletes a brand by it's id")
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        brandService.deleteById(id);
    }

    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "Undelete a brand", description = "Undeletes a brand by it's id")
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{id}/undelete")
    public void undelete(@PathVariable Long id) {
        brandService.undeleteById(id);
    }


    //    @PreAuthorize("hasRole('ROLE_ADMIN')")
    //    @Operation(summary = "Delete a book", description = "Deletes a book by it's id")
    //    @ResponseStatus(HttpStatus.NO_CONTENT)
    //    @DeleteMapping("/{id}")
    //    public void delete(@PathVariable Long id) {
    //        bookService.delete(id);
    //    }

    // Get all products of the brand with categories.

    //    @Operation(summary = "Search a book", description = "Search a book by title and author")
    //    @GetMapping("/search")
    //    public List<BookDto> search(BookSearchParameters searchParameters, Pageable pageable) {
    //    return bookService.search(searchParameters, pageable);
}
