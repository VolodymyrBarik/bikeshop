package org.bikeshop.security;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.bikeshop.repository.WholesaleUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CustomUserDetailsService implements UserDetailsService {
    private final WholesaleUserRepository wholesaleUserRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return wholesaleUserRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Can't find user by email " + email));
    }
}
