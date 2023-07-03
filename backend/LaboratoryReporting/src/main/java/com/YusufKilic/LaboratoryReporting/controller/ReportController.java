package com.YusufKilic.LaboratoryReporting.controller;

import com.YusufKilic.LaboratoryReporting.dto.ReportDto;
import com.YusufKilic.LaboratoryReporting.dto.ReportRequestByLaborant;
import com.YusufKilic.LaboratoryReporting.dto.ReportRequestByPatient;
import com.YusufKilic.LaboratoryReporting.dto.ReportUpdateRequest;
import com.YusufKilic.LaboratoryReporting.service.ReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/getByPatient")
    public ResponseEntity<List<ReportDto>> getReportsByPatient(@RequestBody ReportRequestByPatient request) {
        return ResponseEntity.ok(reportService.getReportsByPatient(request));
    }

    @GetMapping("/getByLaborant")
    public ResponseEntity<List<ReportDto>> getReportsByLaborant(@RequestBody ReportRequestByLaborant request) {
        return ResponseEntity.ok(reportService.getReportsByLaborant(request));
    }

    @PutMapping("/update")
    public ResponseEntity<ReportDto> updateReport(@RequestBody ReportUpdateRequest request) {
        return ResponseEntity.ok(reportService.updateReport(request));
    }

    @DeleteMapping("/{id}")
    public void deleteReportById(@PathVariable Long id) {
        reportService.deleteReportById(id);
    }
}
