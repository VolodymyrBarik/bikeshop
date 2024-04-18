package org.bikeshop.dto.response;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryResponseDto {
    private Long id;
    private String name;
    private String description;
    private boolean isDeleted = false;
    private boolean isEnabled = false;
}
