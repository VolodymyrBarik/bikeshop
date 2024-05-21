package org.bikeshop.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StatusResponseDto {
    private Long id;
    private String name;
    private String message;
    private boolean isActive = false;
    private boolean isDeleted = false;
}
