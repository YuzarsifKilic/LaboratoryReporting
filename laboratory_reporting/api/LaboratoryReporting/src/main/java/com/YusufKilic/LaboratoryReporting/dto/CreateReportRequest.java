package com.YusufKilic.LaboratoryReporting.dto;

import java.time.LocalDateTime;

public record CreateReportRequest (String diagnosisHeader,
                                    String diagnosisDescription,
                                    Long patientId,
                                    Long laborantId) {}
