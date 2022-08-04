package com.api.alvaro.lembrina.security.respositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.alvaro.lembrina.security.entities.User;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByUserName(String userName);
    boolean existsByUserName(String userName);
    
}
