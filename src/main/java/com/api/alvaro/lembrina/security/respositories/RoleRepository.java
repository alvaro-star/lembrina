package com.api.alvaro.lembrina.security.respositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.alvaro.lembrina.security.entities.Role;
import com.api.alvaro.lembrina.security.enums.RoleList;

public interface RoleRepository extends JpaRepository <Role, Integer> {
    Optional<Role> findByRoleName(RoleList roleName);
    
}
