package org.bikeshop.security;

import lombok.RequiredArgsConstructor;
import org.bikeshop.dto.request.UserLoginRequestDto;
import org.bikeshop.dto.request.UserRegistrationRequestDto;
import org.bikeshop.dto.response.UserLoginResponseDto;
import org.bikeshop.dto.response.UserResponseDto;
import org.bikeshop.exception.RegistrationException;
import org.bikeshop.mapper.UserMapper;
import org.bikeshop.model.Role;
import org.bikeshop.model.User;
import org.bikeshop.repository.UserRepository;
import org.bikeshop.service.RoleService;
import org.bikeshop.service.ShoppingCartSupplier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final ShoppingCartSupplier shoppingCartSupplier;

    @Override
    public UserLoginResponseDto authenticate(UserLoginRequestDto request) {
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        String token = jwtUtil.generateToken(authentication.getName());
        return new UserLoginResponseDto(token);
    }

    @Override
    public UserResponseDto register(UserRegistrationRequestDto requestDto)
            throws RegistrationException {
        if (userRepository.findByEmail(requestDto.getEmail()).isPresent()) {
            throw new RegistrationException("Email is already taken, try another one");
        }
        User user = new User();
        user.setEmail(requestDto.getEmail());
        user.setPassword(passwordEncoder.encode(requestDto.getPassword()));
        Role wholesaleUserRole = roleService.getByName(Role.RoleName.WHOLESALE_USER.name());
        user.setRole(wholesaleUserRole);
        user.setFirstName(requestDto.getFirstName());
        user.setLastName(requestDto.getLastName());
        user.setShippingAddress(requestDto.getShippingAddress());
        user.setCompanyName(requestDto.getCompanyName());
        User userFromDb = userRepository.save(user);
        shoppingCartSupplier.createShoppingCart(userFromDb);
        return userMapper.toDto(userFromDb);
    }
}
