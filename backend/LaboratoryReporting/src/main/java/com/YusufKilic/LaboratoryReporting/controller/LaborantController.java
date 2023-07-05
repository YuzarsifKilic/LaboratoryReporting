package com.YusufKilic.LaboratoryReporting.controller;

import com.YusufKilic.LaboratoryReporting.dto.LaborantDto;
import com.YusufKilic.LaboratoryReporting.service.LaborantService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/laborant")
public class LaborantController {

    private final LaborantService laborantService;

    public LaborantController(LaborantService laborantService) {
        this.laborantService = laborantService;
    }

    @GetMapping("/getall")
    public ResponseEntity<List<LaborantDto>> getAllLaborants() {
        return ResponseEntity.ok(laborantService.getAllLaborants());
    }
}
