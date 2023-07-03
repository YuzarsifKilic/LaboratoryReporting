package com.YusufKilic.LaboratoryReporting.dto;

import java.util.Date;

public record ReportUpdateRequest(Long id,
                                  String diagnosisHeader,
                                  String diagnosisDescription) {}
