package com.jhops10.agregador_investimentos.service;

import com.jhops10.agregador_investimentos.controller.dto.CreateUserDto;
import com.jhops10.agregador_investimentos.entity.User;
import com.jhops10.agregador_investimentos.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(CreateUserDto createUserDto) {
        var user = new User();
        user.setUserId(UUID.randomUUID());
        user.setEmail(createUserDto.email());
        user.setUsername(createUserDto.username());
        user.setPassword(createUserDto.password());
        user.setCreationTimestamp(Instant.now());
        user.setUpdateTimestamp(null);

        return userRepository.save(user);
    }
}
