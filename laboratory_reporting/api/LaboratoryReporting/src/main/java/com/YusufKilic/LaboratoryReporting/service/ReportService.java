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

import java.io.IOException;
import java.time.Clock;
import java.time.Instant;
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
    private final ReportDtoConverter converter;
    private final Clock clock;


    public ReportService(ReportRepository repository, LaborantService laborantService, PatientService patientService, ReportDtoConverter converter, Clock clock) {
        this.repository = repository;
        this.laborantService = laborantService;
        this.patientService = patientService;
        this.converter = converter;
        this.clock = clock;
    }

    public ReportDto createReport(CreateReportRequest request) {
        Laborant laborant = laborantService.findLaborantById(request.laborantId());
        Patient patient = patientService.findPatientById(request.patientId());

        Report report = Report.builder()
                .diagnosisHeader(request.diagnosisHeader())
                .diagnosisDescription(request.diagnosisDescription())
                .reportDate(getLocalDateTimeNow())
                .patient(patient)
                .laborant(laborant)
                .build();

        return converter.converter(repository.save(report));
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
        Report report = repository.findById(id)
                .orElseThrow(
                        () -> new ReportNotFoundException("Report didnt find by id : " + id));
        return converter.converter(report);
    }

    public Set<ReportDto> getAllReportByDate() {
        Set<ReportDto> collect = repository.findAll()
                .stream()
                .map(converter::converter)
                .collect(Collectors.toSet());

        Set<ReportDto> reportList = new TreeSet<>();
        reportList.addAll(collect);

        return reportList;
    }

    @Transactional
    public ReportDto updateReport(Long id, ReportUpdateRequest request) {
        Report report = repository.findById(id)
                .orElseThrow(
                        () -> new ReportNotFoundException("Report didnt find by id : " + id));

        report.setDiagnosisHeader(request.diagnosisHeader());
        report.setDiagnosisDescription(request.diagnosisDescription());
        report.setReportDate(LocalDateTime.now());

        Report updatedReport = repository.save(report);

        return converter.converter(updatedReport);
    }

    @Transactional
    public void deleteReportById(Long id) {
        repository.deleteById(id);
    }

    public List<ReportDto> getAllReports() {
        return repository.findAll()
                .stream()
                .map(converter::converter)
                .collect(Collectors.toList());
    }

    public List<ReportDto> getReportsByPatient(ReportRequestByPatient request) {
        long start = System.nanoTime();

        List<Report> reportList = repository.findReportsByPatient(request.firstName(), request.lastName());

        List<ReportDto> collect = reportList.stream()
                .map(converter::converter)
                .collect(Collectors.toList());

        long finish = System.nanoTime();

        System.out.println("\nPatient First Name and Last Name : " + request.firstName() + " " + request.lastName());
        System.out.print("\nStart : " + start);
        System.out.print("\nFinish : " + finish);
        System.out.print("\nTime : " + (finish - start) / 1000000.0 + " ms");

        return collect;
    }

    public List<ReportDto> getReportsByLaborant(ReportRequestByLaborant request) {
        long start = System.nanoTime();
        List<Report> reportList = repository.findReportsByLaborant(request.firstName(), request.lastName());

        List<ReportDto> collect = reportList.stream()
                .map(converter::converter)
                .collect(Collectors.toList());

        long finish = System.nanoTime();

        System.out.println("\nLaborant First Name and Last Name : " + request.firstName()  + " " + request.lastName());
        System.out.print("\nStart : " + start);
        System.out.print("\nFinish : " + finish);
        System.out.print("\nTime : " + (finish - start) / 1000000.0 + " ms");

        return collect;
    }

    private LocalDateTime getLocalDateTimeNow() {
        Instant instant = clock.instant();
        return LocalDateTime.ofInstant(
                instant,
                Clock.systemDefaultZone().getZone());
    }
}
