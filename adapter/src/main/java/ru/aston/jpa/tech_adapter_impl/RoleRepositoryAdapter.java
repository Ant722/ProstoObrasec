package ru.aston.jpa.tech_adapter_impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.aston.app.repositories.RoleRepository;
import ru.aston.exception.RoleNotFoundException;
import ru.aston.jpa.repositories.RoleJpaRepository;
import ru.aston.model.Role;

@Repository
@AllArgsConstructor
public class RoleRepositoryAdapter implements RoleRepository {

    private RoleJpaRepository roleJpaRepository;

    @Override
    public Role findRoleById(Integer id) {
        return roleJpaRepository.getRoleById(id)
                .orElseThrow(() -> new RoleNotFoundException(id));
    }
}
