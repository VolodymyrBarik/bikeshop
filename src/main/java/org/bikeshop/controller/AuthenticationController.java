package org.bikeshop.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.bikeshop.dto.request.UserLoginRequestDto;
import org.bikeshop.dto.request.UserRegistrationRequestDto;
import org.bikeshop.dto.response.UserLoginResponseDto;
import org.bikeshop.dto.response.UserResponseDto;
import org.bikeshop.exception.RegistrationException;
import org.bikeshop.security.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Authentication management", description = "Endpoints to register and authentication")
@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @Operation(summary = "Login", description = "Login into the system")
    @PostMapping("/login")
    public UserLoginResponseDto login(@RequestBody @Valid UserLoginRequestDto requestDto) {
        return authenticationService.authenticate(requestDto);
    }

    @Operation(summary = "Register", description = "Register in the system")
    @PostMapping("/register")
    public UserResponseDto register(
            @RequestBody @Valid UserRegistrationRequestDto requestDto)
            throws RegistrationException {
        return authenticationService.register(requestDto);
    }

    @Operation(summary = "Logout", description = "Logout from the system")
    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        authenticationService.logout(token);
        return ResponseEntity.ok("Ви вийшли з системи");
    }
}

