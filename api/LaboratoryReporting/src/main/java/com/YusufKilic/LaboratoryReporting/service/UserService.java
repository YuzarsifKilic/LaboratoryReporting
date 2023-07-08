package com.YusufKilic.LaboratoryReporting.service;

import com.YusufKilic.LaboratoryReporting.dto.LoginRequest;
import com.YusufKilic.LaboratoryReporting.dto.UserDto;
import com.YusufKilic.LaboratoryReporting.exception.UserNotFoundException;
import com.YusufKilic.LaboratoryReporting.model.User;
import com.YusufKilic.LaboratoryReporting.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public void createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = repository.save(user);

        new UserDto(
                user.getUsername(),
                user.getRole());
    }

    public User findUserByUsername(String username) {
        return repository.findByUsername(username)
                .orElseThrow(
                        () -> new UserNotFoundException("User didnt find by username : " + username));
    }

    public boolean login(LoginRequest request) {
        User user = repository.findByUsername(request.username())
                .orElseThrow(
                        () -> new UserNotFoundException("User didnt find by username : " + request.username()));

        return user.getPassword().equals(request.password());
    }
}
