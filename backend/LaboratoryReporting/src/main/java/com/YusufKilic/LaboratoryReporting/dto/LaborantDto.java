package com.YusufKilic.LaboratoryReporting.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LaborantDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String hospitalNumber;
}
