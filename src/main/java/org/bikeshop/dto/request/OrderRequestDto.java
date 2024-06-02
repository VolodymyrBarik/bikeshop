package org.bikeshop.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequestDto {
    private String shippingAddress;
    private String additionalComment;


}
