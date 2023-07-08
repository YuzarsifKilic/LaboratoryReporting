package com.YusufKilic.LaboratoryReporting.dto;

import com.YusufKilic.LaboratoryReporting.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserDtoConverter {

    public UserDto converter(User from) {
        return new UserDto(
                from.getUsername(),
                from.getRole());
    }
}
