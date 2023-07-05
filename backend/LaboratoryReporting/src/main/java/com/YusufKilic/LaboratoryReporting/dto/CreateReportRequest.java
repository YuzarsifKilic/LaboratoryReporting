package com.YusufKilic.LaboratoryReporting.dto;

import java.time.LocalDateTime;

public record CreateReportRequest (String diagnosisHeader,
                                    Long patientId,
                                    String diagnosisDescription,
                                    Long laborantId) {}
