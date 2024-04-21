package org.bikeshop.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateBrandRequestDto {
    @NotEmpty
    private String name;
    private String description;
    private String logo;
}
