package com.YusufKilic.LaboratoryReporting.dto;

import com.YusufKilic.LaboratoryReporting.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private String username;
    private Role role;
}
