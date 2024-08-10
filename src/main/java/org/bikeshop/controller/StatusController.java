package org.bikeshop.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.bikeshop.dto.request.CreateStatusRequestDto;
import org.bikeshop.dto.response.StatusResponseDto;
import org.bikeshop.service.StatusService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    @Operation(summary = "Enable a status", description = "Enables a status by it's id")
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{id}/enable")
    public void enableBrand(@PathVariable Long id) {
        statusService.enableStatus(id);
    }

    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "Disable a status", description = "Disables a status by it's id")
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{id}/disable")
    public void disableBrand(@PathVariable Long id) {
        statusService.disableStatus(id);
    }

    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "Delete a status", description = "Deletes a status by it's id")
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        statusService.delete(id);
    }
}
