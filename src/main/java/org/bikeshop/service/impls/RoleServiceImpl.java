package org.bikeshop.service.impls;

import lombok.RequiredArgsConstructor;
import org.bikeshop.model.Role;
import org.bikeshop.repository.RoleRepository;
import org.bikeshop.service.RoleService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public Role getByName(String name) {
        return roleRepository.findByRoleName(Role.RoleName.valueOf(name));
    }
}
