package org.bikeshop.security;

import java.util.HashSet;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.bikeshop.dto.request.WholesaleUserLoginRequestDto;
import org.bikeshop.dto.request.WholesaleUserRegistrationRequestDto;
import org.bikeshop.dto.response.UserLoginResponseDto;
import org.bikeshop.dto.response.WholesaleUserResponseDto;
import org.bikeshop.exception.RegistrationException;
import org.bikeshop.mapper.WholesaleUserMapper;
import org.bikeshop.model.Role;
import org.bikeshop.model.ShoppingCart;
import org.bikeshop.model.WholesaleUser;
import org.bikeshop.repository.WholesaleUserRepository;
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
    private final WholesaleUserMapper wholesaleUserMapper;
    private final WholesaleUserRepository wholesaleUserRepository;
    private final RoleService roleService;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final ShoppingCartSupplier shoppingCartSupplier;


    @Override
    public UserLoginResponseDto authenticate(WholesaleUserLoginRequestDto request) {
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        String token = jwtUtil.generateToken(authentication.getName());
        return new UserLoginResponseDto(token);
    }

    @Override
    public WholesaleUserResponseDto register(WholesaleUserRegistrationRequestDto requestDto)
            throws RegistrationException {
        if (wholesaleUserRepository.findByEmail(requestDto.getEmail()).isPresent()) {
            throw new RegistrationException("Email is already taken, try another one");
        }
        WholesaleUser wholesaleUser = new WholesaleUser();
        wholesaleUser.setEmail(requestDto.getEmail());
        wholesaleUser.setPassword(passwordEncoder.encode(requestDto.getPassword()));
        Role wholesaleUserRole = roleService.getByName(Role.RoleName.WHOLESALE_USER.name());
        wholesaleUser.setRole(wholesaleUserRole);
        wholesaleUser.setFirstName(requestDto.getFirstName());
        wholesaleUser.setLastName(requestDto.getLastName());
        wholesaleUser.setShippingAddress(requestDto.getShippingAddress());
        WholesaleUser wholesaleUserFromDb = wholesaleUserRepository.save(wholesaleUser);
        shoppingCartSupplier.createShoppingCart(wholesaleUserFromDb);
        return wholesaleUserMapper.toDto(wholesaleUserFromDb);
    }
}
