package com.YusufKilic.LaboratoryReporting.service;

import com.YusufKilic.LaboratoryReporting.dto.CreateReportRequest;
import com.YusufKilic.LaboratoryReporting.dto.ReportUpdateRequest;
import com.YusufKilic.LaboratoryReporting.model.Laborant;
import com.YusufKilic.LaboratoryReporting.model.Patient;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;

public class TestSupport {

    public Laborant generateLaborant() {
        return Laborant.builder()
                .id(1L)
                .firstName("Yusuf")
                .lastName("Kılıç")
                .hospitalNumber("1234567")
                .build();
    }

    public Patient generatePatient() {
        return Patient.builder()
                .id(1L)
                .firstName("Yusuf")
                .lastName("Kılıç")
                .identificationNumber(1234567890)
                .build();
    }

    public CreateReportRequest generateReportRequest() {
        return new CreateReportRequest("Nezle", "Soğuk Algınlığı", 1L, 1L);
    }

    public ReportUpdateRequest generateReportUpdateRequest() {
        return new ReportUpdateRequest("Update-Nezle", "Update-Soğuk Algınlığı");
    }

    public Instant getCurrentInstant() {
        String instantExpected = "2023-07-08T08:22:25.003153Z";
        Clock clock = Clock.fixed(Instant.parse(instantExpected), Clock.systemDefaultZone().getZone());

        return Instant.now(clock);
    }

    public LocalDateTime getLocalDateTime() {
        return LocalDateTime.ofInstant(getCurrentInstant(), Clock.systemDefaultZone().getZone());
    }
}
