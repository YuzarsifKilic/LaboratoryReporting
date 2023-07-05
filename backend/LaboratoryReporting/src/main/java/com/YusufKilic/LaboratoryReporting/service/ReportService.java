package com.YusufKilic.LaboratoryReporting.service;

import com.YusufKilic.LaboratoryReporting.dto.*;
import com.YusufKilic.LaboratoryReporting.exception.ReportNotFoundException;
import com.YusufKilic.LaboratoryReporting.model.Laborant;
import com.YusufKilic.LaboratoryReporting.model.Patient;
import com.YusufKilic.LaboratoryReporting.model.Report;
import com.YusufKilic.LaboratoryReporting.repository.ReportRepository;
import com.YusufKilic.LaboratoryReporting.utils.ImageUtils;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

@Service
public class ReportService {

    private final ReportRepository repository;
    private final LaborantService laborantService;
    private final PatientService patientService;

    public ReportService(ReportRepository repository, LaborantService laborantService, PatientService patientService) {
        this.repository = repository;
        this.laborantService = laborantService;
        this.patientService = patientService;
    }

    public Long createReport(CreateReportRequest request) {
        Laborant laborant = laborantService.findLaborantById(request.laborantId());
        Patient patient = patientService.findPatientById(request.patientId());

        Report report = Report.builder()
                .diagnosisHeader(request.diagnosisHeader())
                .diagnosisDescription(request.diagnosisDescription())
                .reportDate(LocalDateTime.now())
                .patient(patient)
                .laborant(laborant)
                .build();

        return repository.save(report).getId();
    }

    public void uploadImageToReport(Long id, MultipartFile file) throws IOException {
        Report report = findReportById(id);

         report.setImageData(ImageUtils.compressImage(file.getBytes()));

         repository.save(report);
    }

    public byte[] downloadImage(Long id) {
        Report report = findReportById(id);

        return ImageUtils.decompressImage(report.getImageData());
    }

    private Report findReportById(Long id) {
        return repository.findById(id)
                .orElseThrow(
                        () -> new ReportNotFoundException("Report didnt find by id : " + id));
    }

    public ReportDto findReportByGivenId(Long id) {
        return ReportDtoConverter.converter(findReportById(id));
    }

    public Set<ReportDto> getAllReportByDate() {
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
