package org.bikeshop.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import java.util.List;
import org.bikeshop.dto.request.BrandRequestDto;
import org.bikeshop.dto.response.BrandResponseDto;
import org.bikeshop.model.Brand;
import org.bikeshop.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
    @Operation(summary = "Get all brands", description = "Get a list of all available brands")
    @GetMapping
    public List<BrandResponseDto> getAll() {
        return brandService.findAll();
    }

    @Operation(summary = "Get a brand", description = "Returns a brand by it's id")
    @GetMapping("/{id}")
    public Brand getBookById(@PathVariable Long id) {
        return brandService.findById(id).get();
    }

    //update like in bookService

    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "Create a new brand", description = "Create a new brand")
    @PostMapping
    public void create(@RequestBody @Valid BrandRequestDto requestDto) {
        brandService.save(requestDto);
    }

    //    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "Update a brand", description = "Updates a brand by it's id")
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public void update(@PathVariable Long id,
                       @RequestBody
                       @Valid Brand brand) {
        brandService.update(id, brand);
    }

//    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    @Operation(summary = "Delete a book", description = "Deletes a book by it's id")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    @DeleteMapping("/{id}")
//    public void delete(@PathVariable Long id) {
//        bookService.delete(id);
//    }

//    @Operation(summary = "Search a book", description = "Search a book by title and author")
//    @GetMapping("/search")
//    public List<BookDto> search(BookSearchParameters searchParameters, Pageable pageable) {
//        return bookService.search(searchParameters, pageable);
//    }

}
