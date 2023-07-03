package com.YusufKilic.LaboratoryReporting.dto;

import com.YusufKilic.LaboratoryReporting.model.Report;
import org.springframework.stereotype.Component;

@Component
public class ReportDtoConverter {

    public static ReportDto converter(Report from) {
        return new ReportDto(
                from.getId(),
                PatientDtoConverter.converter(from.getPatient()),
                from.getDiagnosisHeader(),
                from.getDiagnosisDescription(),
                from.getReportDate(),
                LaborantDtoConverter.converter(from.getLaborant()));
    }
}
