package com.amerkhaled.bookingsystem.services;

import com.amerkhaled.bookingsystem.domain.dto.LoginRequestDto;
import com.amerkhaled.bookingsystem.domain.entities.User;
import com.amerkhaled.bookingsystem.exceptions.BadRequestException;
import com.amerkhaled.bookingsystem.exceptions.ResourceNotFoundException;

import java.util.List;

public interface UserService {

    void registerUser(User user);

    boolean loginUser(LoginRequestDto loginRequestDto);

    User getUserByEmail(String email);

    List<User> getAllUsers();

    User updateUser(Long id, User userDetails);

    void deleteUser(Long id);

}
