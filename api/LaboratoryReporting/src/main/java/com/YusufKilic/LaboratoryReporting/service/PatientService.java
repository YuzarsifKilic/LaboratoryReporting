package com.YusufKilic.LaboratoryReporting.service;

import com.YusufKilic.LaboratoryReporting.dto.PatientDto;
import com.YusufKilic.LaboratoryReporting.dto.PatientDtoConverter;
import com.YusufKilic.LaboratoryReporting.exception.PatientNotFoundException;
import com.YusufKilic.LaboratoryReporting.model.Patient;
import com.YusufKilic.LaboratoryReporting.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientService {

    private final PatientRepository repository;
    private final PatientDtoConverter converter;

    public PatientService(PatientRepository repository, PatientDtoConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }

    public List<PatientDto> getAllPatients() {
        return repository.findAll()
                .stream()
                .map(converter::converter)
                .collect(Collectors.toList());
    }

    protected Patient findPatientById(Long id) {
        return repository.findById(id)
                .orElseThrow(
                        () -> new PatientNotFoundException("Patient didnt find by id : " + id));
    }
}
