package com.YusufKilic.LaboratoryReporting.config;

import com.YusufKilic.LaboratoryReporting.model.Role;
import com.YusufKilic.LaboratoryReporting.model.User;
import com.YusufKilic.LaboratoryReporting.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class StartupConfig implements CommandLineRunner {

    private final UserService userService;

    public StartupConfig(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        userService.createUser(new User(1, "yuzarsif", "yuzarsif", Role.USER));
    }
}
