package org.bikeshop.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WholesaleUserResponseDto {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String shippingAddress;
    private String companyName;
}
