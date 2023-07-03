package com.YusufKilic.LaboratoryReporting.dto;

import com.YusufKilic.LaboratoryReporting.model.Laborant;
import org.springframework.stereotype.Component;

@Component
public class LaborantDtoConverter {

    public static LaborantDto converter(Laborant from) {
        return new LaborantDto(
                from.getId(),
                from.getFirstName(),
                from.getLastName(),
                from.getHospitalNumber());
    }
}
