package com.YusufKilic.LaboratoryReporting.service;

import com.YusufKilic.LaboratoryReporting.dto.LoginRequest;
import com.YusufKilic.LaboratoryReporting.exception.UsernameNotFoundException;
import com.YusufKilic.LaboratoryReporting.model.User;
import com.YusufKilic.LaboratoryReporting.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public boolean login(LoginRequest request) {
        User user = repository.findByUsername(request.username())
                .orElseThrow(
                        () -> new UsernameNotFoundException("User didnt find by username : " + request.username()));

        return user.getPassword().equals(request.password());
    }
}
