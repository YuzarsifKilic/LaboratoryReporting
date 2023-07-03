package com.YusufKilic.LaboratoryReporting.controller;

import com.YusufKilic.LaboratoryReporting.dto.ReportDto;
import com.YusufKilic.LaboratoryReporting.service.ReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/report")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/getByDate")
    public ResponseEntity<Set<ReportDto>> getAllReportByDate() {
        return ResponseEntity.ok(reportService.getAllReportByDate());
    }
}
