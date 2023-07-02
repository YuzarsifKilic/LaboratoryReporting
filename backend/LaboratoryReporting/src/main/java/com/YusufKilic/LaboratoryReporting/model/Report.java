package com.YusufKilic.LaboratoryReporting.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Report {

    @Id
    private Long id;
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;
    private String diagnosisHeader;
    private String diagnosisDescription;
    private Date reportDate;
    @ManyToOne
    @JoinColumn(name = "laborant_id")
    private Laborant laborant;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Report report = (Report) o;
        return Objects.equals(id, report.id) && Objects.equals(patient, report.patient) && Objects.equals(diagnosisHeader, report.diagnosisHeader) && Objects.equals(diagnosisDescription, report.diagnosisDescription) && Objects.equals(reportDate, report.reportDate) && Objects.equals(laborant, report.laborant);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, patient, diagnosisHeader, diagnosisDescription, reportDate, laborant);
    }

    @Override
    public String toString() {
        return "Report{" +
                "id=" + id +
                ", patient=" + patient.toString() +
                ", diagnosisHeader='" + diagnosisHeader + '\'' +
                ", diagnosisDescription='" + diagnosisDescription + '\'' +
                ", reportDate=" + reportDate +
                ", laborant=" + laborant.toString() +
                '}';
    }
}
