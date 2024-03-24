package org.bikeshop.security;


import org.bikeshop.dto.request.WholesaleUserLoginRequestDto;
import org.bikeshop.dto.response.UserLoginResponseDto;

public interface AuthenticationService {
    UserLoginResponseDto authenticate(WholesaleUserLoginRequestDto request);

    WholesaleUserResponseDto register(WholesaleUserRegistrationRequestDto requestDto) throws RegistrationException;
}
