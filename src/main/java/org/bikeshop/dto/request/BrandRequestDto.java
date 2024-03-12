package org.bikeshop.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BrandRequestDto {
    @NotEmpty
    private String name;
    private String description;
    private String logo;
}
