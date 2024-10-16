package com.jhops10.agregador_investimentos.service;

import com.jhops10.agregador_investimentos.controller.dto.CreateAccountDto;
import com.jhops10.agregador_investimentos.controller.dto.CreateUserDto;
import com.jhops10.agregador_investimentos.controller.dto.UpdateUserDto;
import com.jhops10.agregador_investimentos.entity.Account;
import com.jhops10.agregador_investimentos.entity.BillingAddress;
import com.jhops10.agregador_investimentos.entity.User;
import com.jhops10.agregador_investimentos.repository.AccountRepository;
import com.jhops10.agregador_investimentos.repository.BillingAddressRepository;
import com.jhops10.agregador_investimentos.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final AccountRepository accountRepository;
    private final BillingAddressRepository billingAddressRepository;

    public UserService(UserRepository userRepository, AccountRepository accountRepository, BillingAddressRepository billingAddressRepository) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
        this.billingAddressRepository = billingAddressRepository;
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

    public void createAccount(String userId, CreateAccountDto createAccountDto) {

        var user = userRepository.findById(UUID.fromString(userId))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        //DTO -> Entity

        var account = new Account(
                UUID.randomUUID(),
                createAccountDto.description(),
                user,
                null,
                new ArrayList<>()
        );

        var accountCreated = accountRepository.save(account);

        var billingAddress = new BillingAddress(
            accountCreated.getAccount_id(),
                createAccountDto.street(),
                createAccountDto.number(),
                account
        );

        billingAddressRepository.save(billingAddress);
    }
}
