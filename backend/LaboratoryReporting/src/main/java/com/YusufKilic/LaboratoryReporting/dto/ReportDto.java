package com.YusufKilic.LaboratoryReporting.dto;

import com.YusufKilic.LaboratoryReporting.model.Report;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReportDto implements Comparable {

    private Long id;
    private PatientDto patient;
    private String diagnosisHeader;
    private String diagnosisDescription;
    private LocalDateTime reportDate;
    private LaborantDto laborant;

    @Override
    public int compareTo(Object o) {
        ReportDto r = (ReportDto) o;
        return reportDate.compareTo(r.getReportDate());
    }
}
