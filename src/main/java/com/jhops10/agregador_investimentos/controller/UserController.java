package com.jhops10.agregador_investimentos.controller;

import com.jhops10.agregador_investimentos.controller.dto.CreateUserDto;
import com.jhops10.agregador_investimentos.entity.User;
import com.jhops10.agregador_investimentos.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

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
        //
        return null;
    }

}
