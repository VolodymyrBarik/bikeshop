package org.bikeshop.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateStatusRequestDto {
    private String name;
    private String message;
    private boolean isActive = false;
    private boolean isDeleted = false;
}
