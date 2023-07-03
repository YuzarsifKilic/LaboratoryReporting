package com.YusufKilic.LaboratoryReporting.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReportDto {

    private Long id;
    private PatientDto patient;
    private String diagnosisHeader;
    private String diagnosisDescription;
    private LocalDateTime reportDate;
    private LaborantDto laborant;
}
