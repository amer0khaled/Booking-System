package com.amerkhaled.bookingsystem.services.impl;

import com.amerkhaled.bookingsystem.domain.dto.LoginRequestDto;
import com.amerkhaled.bookingsystem.domain.entities.User;
import com.amerkhaled.bookingsystem.exceptions.BadRequestException;
import com.amerkhaled.bookingsystem.exceptions.ResourceNotFoundException;
import com.amerkhaled.bookingsystem.repositories.UserRepository;
import com.amerkhaled.bookingsystem.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    private boolean isUserEmailExist(String email) {
        return userRepository.existsByEmail(email);
    }

    private String encodePassword(String password) {
        return bCryptPasswordEncoder.encode(password);
    }

    public void registerUser(User user) {
        if (isUserEmailExist(user.getEmail())) {
            throw new BadRequestException("User already exists with email: " + user.getEmail());
        }

        user.setPassword(encodePassword(user.getPassword()));
        userRepository.save(user);
    }

    public boolean loginUser(LoginRequestDto loginRequestDto) {
        User user = userRepository.findByEmail(loginRequestDto.email())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + loginRequestDto.email()));
        return bCryptPasswordEncoder.matches(loginRequestDto.password(), user.getPassword());
    }


    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + email));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User updateUser(Long id, User userDetails) {
        User userToUpdate = userRepository.findById(userDetails.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

        userToUpdate.setName(userDetails.getName());
        userToUpdate.setEmail(userDetails.getEmail());
        userToUpdate.setPassword(encodePassword(userDetails.getPassword()));
        userToUpdate.setRole(userDetails.getRole());

        return userRepository.save(userToUpdate);

    }

    public void deleteUser(Long id) {
        User userToDelete = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

        userRepository.delete(userToDelete);
    }

}
