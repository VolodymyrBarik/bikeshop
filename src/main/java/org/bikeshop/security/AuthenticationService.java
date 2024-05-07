package org.bikeshop.security;


import org.bikeshop.dto.request.UserLoginRequestDto;
import org.bikeshop.dto.request.UserRegistrationRequestDto;
import org.bikeshop.dto.response.UserLoginResponseDto;
import org.bikeshop.dto.response.UserResponseDto;
import org.bikeshop.exception.RegistrationException;

public interface AuthenticationService {
    UserLoginResponseDto authenticate(UserLoginRequestDto request);

    UserResponseDto register(UserRegistrationRequestDto requestDto) throws
            RegistrationException;
}
