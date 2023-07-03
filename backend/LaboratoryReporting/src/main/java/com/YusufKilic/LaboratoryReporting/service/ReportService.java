package com.YusufKilic.LaboratoryReporting.service;

import com.YusufKilic.LaboratoryReporting.dto.ReportDto;
import com.YusufKilic.LaboratoryReporting.dto.ReportDtoConverter;
import com.YusufKilic.LaboratoryReporting.dto.ReportUpdateRequest;
import com.YusufKilic.LaboratoryReporting.exception.ReportNotFoundException;
import com.YusufKilic.LaboratoryReporting.model.Report;
import com.YusufKilic.LaboratoryReporting.repository.ReportRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

@Service
public class ReportService {

    private final ReportRepository repository;

    public ReportService(ReportRepository repository) {
        this.repository = repository;
    }

    public Set<ReportDto> getAllReportByDate() {
        Set<Report> reportList = new TreeSet<>(repository.findAll());

        return reportList.stream()
                .map(r -> ReportDtoConverter.converter(r))
                .collect(Collectors.toSet());
    }

    @Transactional
    public void updateReport(ReportUpdateRequest request) {
        Report report = repository.findById(request.id())
                .orElseThrow(
                        () -> new ReportNotFoundException("Report didnt find by id : " + request.id()));

        report.setDiagnosisHeader(request.diagnosisHeader());
        report.setDiagnosisDescription(request.diagnosisDescription());
        report.setReportDate(LocalDateTime.now());

        repository.save(report);
    }

    @Transactional
    public void deleteReportById(Long id) {
        repository.deleteById(id);
    }
}
