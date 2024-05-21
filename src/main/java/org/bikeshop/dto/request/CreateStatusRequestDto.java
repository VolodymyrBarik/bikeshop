package org.bikeshop.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateStatusRequestDto {
    private String name;
    private String message;
    @JsonProperty("isActive")
    private boolean isActive = false;
    private boolean isDeleted = false;
}
