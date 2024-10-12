package com.jhops10.agregador_investimentos.service;

import com.jhops10.agregador_investimentos.controller.dto.CreateUserDto;
import com.jhops10.agregador_investimentos.controller.dto.UpdateUserDto;
import com.jhops10.agregador_investimentos.entity.User;
import com.jhops10.agregador_investimentos.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
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

    public Optional<User> getUserById(String userId) {
        return userRepository.findById(UUID.fromString(userId));

    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void updateUserById(String userId,
                               UpdateUserDto updateUserDto) {

        var userEntity = userRepository.findById(UUID.fromString(userId));

        if (userEntity.isPresent()) {
            var user = userEntity.get();

            if (updateUserDto.username() != null) {
                user.setUsername(updateUserDto.username());
            }

            if (updateUserDto.password() != null) {
                user.setPassword(updateUserDto.password());
            }

            userRepository.save(user);
        }

    }

    public void deleteUserById(String userId) {
        var userExist = userRepository.existsById(UUID.fromString(userId));

        if (userExist) {
            userRepository.deleteById(UUID.fromString(userId));
        }


    }

}
