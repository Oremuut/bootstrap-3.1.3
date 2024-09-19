package ru.kata.bootstrap.service;

import org.springframework.stereotype.Service;
import ru.kata.bootstrap.repositories.RoleRepository;
import ru.kata.bootstrap.models.Role;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }
}
