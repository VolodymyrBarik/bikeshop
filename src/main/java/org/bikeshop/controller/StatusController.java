package org.bikeshop.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.bikeshop.dto.request.CreateBrandRequestDto;
import org.bikeshop.dto.request.CreateStatusRequestDto;
import org.bikeshop.dto.response.BrandResponseDto;
import org.bikeshop.dto.response.StatusResponseDto;
import org.bikeshop.service.StatusService;
import org.springframework.data.domain.Pageable;
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
@RequestMapping("/statuses")
public class StatusController {
    private final StatusService statusService;

    @Operation(summary = "Get all enabled, non deleted statuses",
            description = "Get a list of all enabled, non deleted statuses")
    @GetMapping("/active")

    public List<StatusResponseDto> getAllEnabledNonDeleted(Pageable pageable) {
        return statusService.findAllActiveNonDeleted(pageable);
    }

    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "Get all", description = "Get a list of all statuses")
    @GetMapping("/all")
    public List<StatusResponseDto> getAll() {
        return statusService.findAll();
    }

    @Operation(summary = "Get a status", description = "Returns a status by it's id")
    @GetMapping("/{id}")
    public StatusResponseDto getById(@PathVariable Long id) {
        return statusService.findById(id);
    }

    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "Create a new status", description = "Create a new status")
    @PostMapping
    public StatusResponseDto create(@RequestBody @Valid CreateStatusRequestDto requestDto) {
        return statusService.save(requestDto);
    }

    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "Update a status", description = "Updates a status by it's id")
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public void update(@PathVariable Long id,
                       @RequestBody
                       @Valid CreateStatusRequestDto requestDto) {
        statusService.update(id, requestDto);
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
}
