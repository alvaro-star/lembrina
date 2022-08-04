package com.api.alvaro.lembrina.security.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.api.alvaro.lembrina.security.entities.User;
import com.api.alvaro.lembrina.security.respositories.UserRepository;

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> getByUserName(String userName){
        return userRepository.findByUserName(userName);
    }
    public boolean existByUserName(String userName){
        return userRepository.existsByUserName(userName);
    }
    public void save(User user){
        userRepository.save(user);
    }
    
}
