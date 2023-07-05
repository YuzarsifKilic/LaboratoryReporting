package com.YusufKilic.LaboratoryReporting.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Report implements Comparable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;
    private String diagnosisHeader;
    private String diagnosisDescription;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ", shape = JsonFormat.Shape.STRING)
    private LocalDateTime reportDate;
    @Lob
    @Column(name = "imagedata",length = 1000)
    private byte[] imageData;
    @ManyToOne
    @JoinColumn(name = "laborant_id")
    private Laborant laborant;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Report report = (Report) o;
        return Objects.equals(id, report.id) && Objects.equals(patient, report.patient) && Objects.equals(diagnosisHeader, report.diagnosisHeader) && Objects.equals(diagnosisDescription, report.diagnosisDescription) && Objects.equals(reportDate, report.reportDate) && Arrays.equals(imageData, report.imageData) && Objects.equals(laborant, report.laborant);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, patient, diagnosisHeader, diagnosisDescription, reportDate, laborant);
        result = 31 * result + Arrays.hashCode(imageData);
        return result;
    }

    @Override
    public String toString() {
        return "Report{" +
                "id=" + id +
                ", patient=" + patient +
                ", diagnosisHeader='" + diagnosisHeader + '\'' +
                ", diagnosisDescription='" + diagnosisDescription + '\'' +
                ", reportDate=" + reportDate +
                ", laborant=" + laborant +
                '}';
    }


    @Override
    public int compareTo(Object o) {
        Report r = (Report) o;
        return reportDate.compareTo(r.getReportDate());
    }
}
