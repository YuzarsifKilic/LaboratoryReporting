package com.YusufKilic.LaboratoryReporting.dto;

import com.YusufKilic.LaboratoryReporting.model.Patient;
import org.springframework.stereotype.Component;

@Component
public class PatientDtoConverter {

    public static PatientDto converter(Patient from) {
        return new PatientDto(
                from.getId(),
                from.getFirstName(),
                from.getLastName(),
                from.getIdentificationNumber());
    }
}
