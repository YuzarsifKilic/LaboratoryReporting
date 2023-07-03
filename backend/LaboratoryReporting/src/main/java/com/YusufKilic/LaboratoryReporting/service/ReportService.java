package com.YusufKilic.LaboratoryReporting.service;

import com.YusufKilic.LaboratoryReporting.dto.*;
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
        //Set<Report> reportList = new TreeSet<>(repository.findAll());

        Set<ReportDto> collect = repository.findAll()
                .stream()
                .map(r -> ReportDtoConverter.converter(r))
                .collect(Collectors.toSet());

        Set<ReportDto> reportList = new TreeSet<>();
        reportList.addAll(collect);

        return reportList;
    }

    @Transactional
    public ReportDto updateReport(ReportUpdateRequest request) {
        Report report = repository.findById(request.id())
                .orElseThrow(
                        () -> new ReportNotFoundException("Report didnt find by id : " + request.id()));

        report.setDiagnosisHeader(request.diagnosisHeader());
        report.setDiagnosisDescription(request.diagnosisDescription());
        report.setReportDate(LocalDateTime.now());

        Report updatedReport = repository.save(report);

        return ReportDtoConverter.converter(updatedReport);
    }

    @Transactional
    public void deleteReportById(Long id) {
        repository.deleteById(id);
    }

    public List<ReportDto> getReportsByPatientIdentificationNumber(int id) {
        return repository.findAll()
                .stream()
                .filter(r -> r.getPatient().getIdentificationNumber() == id)
                .map(ReportDtoConverter::converter)
                .collect(Collectors.toList());
    }

    public List<ReportDto> getReportsByPatient(ReportRequestByPatient request) {
        return repository.findAll()
                .stream()
                .filter(r ->
                        r.getPatient().getFirstName().equals(request.firstName()) &&
                        r.getPatient().getLastName().equals(request.lastName()))
                .map(ReportDtoConverter::converter)
                .collect(Collectors.toList());
    }

    public List<ReportDto> getReportsByLaborant(ReportRequestByLaborant request) {
        return repository.findAll()
                .stream()
                .filter(r ->
                        r.getLaborant().getFirstName().equals(request.firstName()) &&
                        r.getLaborant().getLastName().equals(request.lastName()))
                .map(ReportDtoConverter::converter)
                .collect(Collectors.toList());
    }
}
