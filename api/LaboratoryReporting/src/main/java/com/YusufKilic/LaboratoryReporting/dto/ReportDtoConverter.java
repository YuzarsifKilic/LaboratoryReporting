package com.YusufKilic.LaboratoryReporting.dto;

import com.YusufKilic.LaboratoryReporting.model.Report;
import org.springframework.stereotype.Component;

@Component
public class ReportDtoConverter {

    public static ReportDto converter(Report from) {
        return new ReportDto(
                from.getId(),
                from.getPatient().getFirstName(),
                from.getPatient().getLastName(),
                from.getPatient().getIdentificationNumber(),
                from.getDiagnosisHeader(),
                from.getDiagnosisDescription(),
                from.getReportDate(),
                from.getLaborant().getFirstName(),
                from.getLaborant().getLastName(),
                from.getLaborant().getHospitalNumber());
    }
}
