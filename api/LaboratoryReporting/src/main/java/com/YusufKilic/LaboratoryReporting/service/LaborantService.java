package com.YusufKilic.LaboratoryReporting.service;

import com.YusufKilic.LaboratoryReporting.dto.LaborantDto;
import com.YusufKilic.LaboratoryReporting.dto.LaborantDtoConverter;
import com.YusufKilic.LaboratoryReporting.exception.LaborantNotFoundException;
import com.YusufKilic.LaboratoryReporting.model.Laborant;
import com.YusufKilic.LaboratoryReporting.repository.LaborantRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LaborantService {

    private final LaborantRepository repository;
    private final LaborantDtoConverter converter;

    public LaborantService(LaborantRepository repository, LaborantDtoConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }

    public List<LaborantDto> getAllLaborants() {
        return repository.findAll()
                .stream()
                .map(converter::converter)
                .collect(Collectors.toList());
    }

    protected Laborant findLaborantById(Long id) {
        return repository.findById(id)
                .orElseThrow(
                        () -> new LaborantNotFoundException("Laborant didnt find by id : " + id));
    }
}
