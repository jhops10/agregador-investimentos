package com.jhops10.agregador_investimentos.controller;

import com.jhops10.agregador_investimentos.controller.dto.AccountResponseDto;
import com.jhops10.agregador_investimentos.controller.dto.CreateAccountDto;
import com.jhops10.agregador_investimentos.controller.dto.CreateUserDto;
import com.jhops10.agregador_investimentos.controller.dto.UpdateUserDto;
import com.jhops10.agregador_investimentos.entity.Account;
import com.jhops10.agregador_investimentos.entity.User;
import com.jhops10.agregador_investimentos.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody CreateUserDto createUserDto) {
        var createdUser = userService.createUser(createUserDto);
        return ResponseEntity.created(URI.create("/v1/users")).body(createdUser);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUser(@PathVariable("userId") String userId) {
        var user = userService.getUserById(userId);

        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<User>> findAllUsers() {
        var users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Void> updateUserById(@PathVariable("userId") String userId,
                                               @RequestBody UpdateUserDto updateUserDto) {

        userService.updateUserById(userId, updateUserDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteById(@PathVariable("userId") String userId) {
        userService.deleteUserById(userId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{userId}/accounts")
    public ResponseEntity<Void> createAccount(@PathVariable("userId") String userId,
                                           @RequestBody CreateAccountDto createAccountDto) {
        userService.createAccount(userId, createAccountDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{userId}/accounts")
    public ResponseEntity<List<AccountResponseDto>> getAccounts(@PathVariable("userId") String userId) {
        var accounts = userService.getAllAccounts(userId);
        return ResponseEntity.ok(accounts);
    }


}
