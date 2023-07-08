package com.YusufKilic.LaboratoryReporting.controller;

import com.YusufKilic.LaboratoryReporting.dto.*;
import com.YusufKilic.LaboratoryReporting.service.ReportService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/report")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @PostMapping("/create")
    public ResponseEntity<ReportDto> createReport(@RequestBody CreateReportRequest request) {
        return ResponseEntity.ok(reportService.createReport(request));
    }

    @GetMapping("/getReport/{id}")
    public ResponseEntity<ReportDto> getReportById(@PathVariable Long id) {
        return ResponseEntity.ok(reportService.findReportByGivenId(id));
    }

    @PostMapping("/saveImage/{id}")
    public void uploadImage(@RequestParam("image")MultipartFile file, @PathVariable Long id) throws IOException {
        reportService.uploadImageToReport(id, file);
    }

    @GetMapping("/getImage/{id}")
    public ResponseEntity<?> downloadImage(@PathVariable Long id) {
        byte[] bytes = reportService.downloadImage(id);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(bytes);
    }

    @GetMapping("/getByDate")
    public ResponseEntity<Set<ReportDto>> getAllReportByDate() {
        return ResponseEntity.ok(reportService.getAllReportByDate());
    }

    @GetMapping("/getall")
    public ResponseEntity<List<ReportDto>> getAllReports() {
        return ResponseEntity.ok(reportService.getAllReports());
    }

    @PostMapping("/getByPatient")
    public ResponseEntity<List<ReportDto>> getReportsByPatient(@RequestBody ReportRequestByPatient request) {
        return ResponseEntity.ok(reportService.getReportsByPatient(request));
    }

    @PostMapping("/getByLaborant")
    public ResponseEntity<List<ReportDto>> getReportsByLaborant(@RequestBody ReportRequestByLaborant request) {
        return ResponseEntity.ok(reportService.getReportsByLaborant(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReportDto> updateReport(@PathVariable Long id, @RequestBody ReportUpdateRequest request) {
        return ResponseEntity.ok(reportService.updateReport(id, request));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteReportById(@PathVariable Long id) {
        reportService.deleteReportById(id);
    }
}
