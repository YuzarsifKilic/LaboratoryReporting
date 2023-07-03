package com.YusufKilic.LaboratoryReporting.service;

import com.YusufKilic.LaboratoryReporting.dto.LaborantDto;
import com.YusufKilic.LaboratoryReporting.dto.LaborantDtoConverter;
import com.YusufKilic.LaboratoryReporting.repository.LaborantRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LaborantService {

    private final LaborantRepository repository;

    public LaborantService(LaborantRepository repository) {
        this.repository = repository;
    }

    public List<LaborantDto> getAllLaborants() {
        return repository.findAll()
                .stream()
                .map(LaborantDtoConverter::converter)
                .collect(Collectors.toList());
    }
}
