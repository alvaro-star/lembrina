package com.api.alvaro.lembrina.security.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.api.alvaro.lembrina.security.entities.Role;
import com.api.alvaro.lembrina.security.enums.RoleList;
import com.api.alvaro.lembrina.security.respositories.RoleRepository;

@Service
@Transactional
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
    
    public Optional<Role> getByRoleName(RoleList roleName){
        return roleRepository.findByRoleName(roleName);
    }
    
    
}
