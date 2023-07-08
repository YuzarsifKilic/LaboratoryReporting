package com.YusufKilic.LaboratoryReporting.service;

import com.YusufKilic.LaboratoryReporting.dto.CreateReportRequest;
import com.YusufKilic.LaboratoryReporting.dto.ReportDto;
import com.YusufKilic.LaboratoryReporting.dto.ReportDtoConverter;
import com.YusufKilic.LaboratoryReporting.dto.ReportUpdateRequest;
import com.YusufKilic.LaboratoryReporting.exception.LaborantNotFoundException;
import com.YusufKilic.LaboratoryReporting.exception.PatientNotFoundException;
import com.YusufKilic.LaboratoryReporting.exception.ReportNotFoundException;
import com.YusufKilic.LaboratoryReporting.model.Report;
import com.YusufKilic.LaboratoryReporting.repository.ReportRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReportServiceTest extends TestSupport {

    private ReportService reportService;
    private ReportRepository reportRepository;
    private LaborantService laborantService;
    private PatientService patientService;
    private ReportDtoConverter converter;

    @BeforeEach
    public void setUp() {
        reportRepository = mock(ReportRepository.class);
        laborantService = mock(LaborantService.class);
        patientService = mock(PatientService.class);
        converter = mock(ReportDtoConverter.class);
        Clock clock =mock(Clock.class);

        reportService = new ReportService(reportRepository, laborantService, patientService, converter, clock);

        when(clock.instant()).thenReturn(getCurrentInstant());
        when(clock.getZone()).thenReturn(Clock.systemDefaultZone().getZone());
    }

    @Test
    public void testFindReportByGivenId_whenReportIdExists_shouldReturnReportDto() {
        byte[] image = new byte[0];
        Report report = new Report(1L, generatePatient(), "Nezle", "Soğuk Algınlığı", getLocalDateTime(), image, generateLaborant());
        ReportDto reportDto = new ReportDto(1L, "Yusuf", "Kılıç", 1234567890, "Nezle", "Soğuk Algınlığı", getLocalDateTime(), "Yusuf", "Kılıç", "1234567");

        when(reportRepository.findById(1L)).thenReturn(Optional.of(report));
        when(converter.converter(report)).thenReturn(reportDto);

        ReportDto result = reportService.findReportByGivenId(1L);

        assertEquals(result, reportDto);

    }

    @Test
    public void testFindReportByGivenId_whenReportIdDoesNotExists_shouldReturnReportNotFoundException() {

        when(reportRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ReportNotFoundException.class, () -> reportService.findReportByGivenId(1L));
    }

    @Test
    public void testCreateReport_whenLaborantIdExistsAndPatientIdExists_shouldReturnReportDto() {
        CreateReportRequest request = generateReportRequest();

        Report report = Report.builder()
                .patient(generatePatient())
                .diagnosisHeader("Nezle")
                .diagnosisDescription("Soğuk Algınlığı")
                .laborant(generateLaborant())
                .reportDate(getLocalDateTime())
                .build();
        Report savedReport = Report.builder()
                .id(1L)
                .patient(generatePatient())
                .diagnosisHeader("Nezle")
                .diagnosisDescription("Soğuk Algınlığı")
                .laborant(generateLaborant())
                .reportDate(getLocalDateTime())
                .build();
        ReportDto reportDto = new ReportDto(1L, "Yusuf", "Kılıç", 1234567890, "Nezle", "Soğuk Algınlığı", getLocalDateTime(), "Yusuf", "Kılıç", "1234567");

        when(laborantService.findLaborantById(1L)).thenReturn(generateLaborant());
        when(patientService.findPatientById(1L)).thenReturn(generatePatient());
        when(reportRepository.save(report)).thenReturn(savedReport);
        when(converter.converter(savedReport)).thenReturn(reportDto);

        ReportDto result = reportService.createReport(request);

        assertEquals(result, reportDto);
    }

    @Test
    public void testCreateReport_whenLaborantIdDoesNotExist_shouldReturnLaborantNotFoundException() {
        CreateReportRequest request = generateReportRequest();

        when(laborantService.findLaborantById(1L)).thenThrow(new LaborantNotFoundException("test"));

        assertThrows(LaborantNotFoundException.class, () -> reportService.createReport(request));

        verify(laborantService).findLaborantById(request.laborantId());

        verifyNoInteractions(reportRepository);
        verifyNoInteractions(patientService);
        verifyNoInteractions(converter);
    }

    @Test
    public void testCreateReport_whenLaborantIdExistButPatientIdDoesNotExist_shouldReturnPatientNotFoundException() {
        CreateReportRequest request = generateReportRequest();

        when(laborantService.findLaborantById(1L)).thenReturn(generateLaborant());

        when(patientService.findPatientById(1L)).thenThrow(new PatientNotFoundException("test"));

        assertThrows(PatientNotFoundException.class, () -> reportService.createReport(request));

        verify(patientService).findPatientById(request.patientId());

        verifyNoInteractions(reportRepository);
        verifyNoInteractions(converter);
    }

    @Test
    public void testUpdateReport_whenIdExist_shouldReturnUpdatedReport() {
        ReportUpdateRequest request = generateReportUpdateRequest();
        byte[] image = new byte[0];
        Report report = new Report(1L, generatePatient(), "Nezle", "Soğuk Algınlığı", getLocalDateTime(), image, generateLaborant());
        Report updatedReport = new Report(1L, generatePatient(), "Update-Nezle", "Update-Soğuk Algınlığı", getLocalDateTime(), image, generateLaborant());
        ReportDto reportDto = new ReportDto(1L, "Yusuf", "Kılıç", 1234567890, "Update-Nezle", "Update-Soğuk Algınlığı", getLocalDateTime(), "Yusuf", "Kılıç", "1234567");

        when(reportRepository.findById(1L)).thenReturn(Optional.of(report));

        report.setDiagnosisHeader(request.diagnosisHeader());
        report.setDiagnosisDescription(request.diagnosisDescription());
        report.setReportDate(getLocalDateTime());

        when(reportRepository.save(report)).thenReturn(updatedReport);
        when(converter.converter(updatedReport)).thenReturn(reportDto);

        ReportDto result = reportService.updateReport(1L, request);

        assertEquals(result, reportDto);

    }

    @Test
    public void testUpdateReport_whenReportIdDoesNotExist_shouldReturnReportNotFoundException() {
        ReportUpdateRequest request = generateReportUpdateRequest();

        when(reportRepository.findById(1L)).thenThrow(new ReportNotFoundException("test"));

        assertThrows(ReportNotFoundException.class, () -> reportService.updateReport(1L, request));

        verify(reportRepository).findById(1L);

        verifyNoInteractions(converter);
    }

}