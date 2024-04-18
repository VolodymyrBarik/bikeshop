package org.bikeshop.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCategoryRequestDto {
    private String name;
    private String description;
}
