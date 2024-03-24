package org.bikeshop.security;

import org.bikeshop.dto.request.WholesaleUserLoginRequestDto;
import org.bikeshop.dto.request.WholesaleUserRegistrationRequestDto;
import org.bikeshop.dto.response.UserLoginResponseDto;
import org.bikeshop.dto.response.WholesaleUserResponseDto;
import org.bikeshop.exception.RegistrationException;

public class AuthenticationServiceImpl implements AuthenticationService {
    @Override
    public UserLoginResponseDto authenticate(WholesaleUserLoginRequestDto request) {
        return null;
    }

    @Override
    public WholesaleUserResponseDto register(WholesaleUserRegistrationRequestDto requestDto)
            throws RegistrationException {
        return null;
    }
}
