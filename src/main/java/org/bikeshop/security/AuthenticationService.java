package org.bikeshop.security;


import org.bikeshop.dto.request.WholesaleUserLoginRequestDto;
import org.bikeshop.dto.request.WholesaleUserRegistrationRequestDto;
import org.bikeshop.dto.response.UserLoginResponseDto;
import org.bikeshop.dto.response.UserResponseDto;
import org.bikeshop.exception.RegistrationException;

public interface AuthenticationService {
    UserLoginResponseDto authenticate(WholesaleUserLoginRequestDto request);

    UserResponseDto register(WholesaleUserRegistrationRequestDto requestDto) throws
            RegistrationException;
}
