package com.amerkhaled.bookingsystem.controllers;

import com.amerkhaled.bookingsystem.domain.dto.LoginRequestDto;
import com.amerkhaled.bookingsystem.domain.dto.UserRequestDto;
import com.amerkhaled.bookingsystem.domain.dto.UserResponseDto;
import com.amerkhaled.bookingsystem.domain.dto.UserUpdateDto;
import com.amerkhaled.bookingsystem.domain.entities.User;
import com.amerkhaled.bookingsystem.exceptions.BadRequestException;
import com.amerkhaled.bookingsystem.mappers.UserMapper;
import com.amerkhaled.bookingsystem.services.impl.UserServiceImpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/booking-system/users")
@CrossOrigin
public class UserController {

    private UserServiceImpl userService;
    private UserMapper userMapper;

    @Autowired
    public UserController(UserServiceImpl userService,
                          UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(
            @RequestBody UserRequestDto userRequestDto) {

        // Map UserRequestDto to User Entity
        User user = userMapper.fromRequestDto(userRequestDto);

        // Register User Entity
        userService.registerUser(user);

        // Return Response
        return ResponseEntity.ok("User registered successfully");

    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(
            @RequestBody LoginRequestDto loginRequestDto) {

        boolean isLoginSuccessful = userService.loginUser(loginRequestDto);
        return isLoginSuccessful ? ResponseEntity.ok("Login successful")
                : ResponseEntity.badRequest().body("Login failed");

    }

    @GetMapping("/by-email")
    public ResponseEntity<UserResponseDto> getUserByEmail(@RequestParam(required = true) String email) {
        User user = userService.getUserByEmail(email);
        UserResponseDto userResponseDto = userMapper.toResponseDto(user);
        return ResponseEntity.ok(userResponseDto);
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        List<UserResponseDto> userResponseDtos = users.stream()
                .map(user -> userMapper.toResponseDto(user))
                .toList();

        return ResponseEntity.ok(userResponseDtos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable Long id,
                                                      @RequestBody UserUpdateDto userDetailsDto) {
                                                
        if (!id.equals(userDetailsDto.id())) {
            throw new BadRequestException("Path ID and UserDetailsDTO ID do not match");
        }

        User userBeforeUpdate = userMapper.fromUpdateDto(userDetailsDto);
        User updatedUser = userService.updateUser(id, userBeforeUpdate);
        UserResponseDto userResponseDto = userMapper.toResponseDto(updatedUser);
        return ResponseEntity.ok(userResponseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully");
    }

}
