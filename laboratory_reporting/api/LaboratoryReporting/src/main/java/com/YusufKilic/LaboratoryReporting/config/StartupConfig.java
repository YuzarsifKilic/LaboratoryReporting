package com.YusufKilic.LaboratoryReporting.config;

import com.YusufKilic.LaboratoryReporting.model.Laborant;
import com.YusufKilic.LaboratoryReporting.model.Patient;
import com.YusufKilic.LaboratoryReporting.model.Role;
import com.YusufKilic.LaboratoryReporting.model.User;
import com.YusufKilic.LaboratoryReporting.repository.LaborantRepository;
import com.YusufKilic.LaboratoryReporting.repository.PatientRepository;
import com.YusufKilic.LaboratoryReporting.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class StartupConfig implements CommandLineRunner {

    private final UserService userService;
    private final LaborantRepository laborantRepository;
    private final PatientRepository patientRepository;
    private final ReportService reportService;

    public StartupConfig(UserService userService,
                         LaborantRepository laborantRepository,
                         PatientRepository patientRepository,
                         ReportService reportService) {
        this.userService = userService;
        this.laborantRepository = laborantRepository;
        this.patientRepository = patientRepository;
        this.reportService = reportService;
    }

    @Override
    public void run(String... args) throws Exception {
        userService.createUser(new User(1, "yuzarsif", "yuzarsif", Role.USER));
        userService.createUser(new User(2, "admin", "admin", Role.ADMIN));
        Laborant laborant = Laborant.builder()
                .id(1L)
                .firstName("Yusuf")
                .lastName("Kılıç")
                .hospitalNumber("1234567")
                .build();
        laborantRepository.save(laborant);

        Laborant laborant2 = Laborant.builder()
                .id(2L)
                .firstName("Ömer")
                .lastName("Budak")
                .hospitalNumber("1234567")
                .build();
        laborantRepository.save(laborant2);

        Laborant laborant3 = Laborant.builder()
                .id(3L)
                .firstName("Ahmet")
                .lastName("Kılıç")
                .build();
        laborantRepository.save(laborant3);

        Laborant laborant4 = Laborant.builder()
                .id(4L)
                .firstName("Yusuf")
                .lastName("Budak")
                .build();
        laborantRepository.save(laborant4);
        Patient patient = Patient.builder()
                .id(1L)
                .firstName("Zeynep")
                .lastName("Kılıç")
                .identificationNumber(1234567890)
                .build();
        patientRepository.save(patient);

        Patient patient2 = Patient.builder()
                .id(2L)
                .firstName("Elif")
                .lastName("Taşçı")
                .identificationNumber(1234567890)
                .build();
        patientRepository.save(patient2);

        Patient patient3 = Patient.builder()
                .id(3L)
                .firstName("Zeynep")
                .lastName("Taşçı")
                .identificationNumber(1234527890)
                .build();
        patientRepository.save(patient3);

        Patient patient4 = Patient.builder()
                .id(4L)
                .firstName("Elif")
                .lastName("Kılıç")
                .identificationNumber(1234521890)
                .build();
        patientRepository.save(patient4);

        createReport(1L, 1L);
        createReport(1L, 2L);
        createReport(1L, 3L);
        createReport(1L, 4L);
        createReport(2L, 1L);
        createReport(2L, 2L);
        createReport(2L, 3L);
        createReport(2L, 4L);
        createReport(3L, 1L);
        createReport(3L, 2L);
        createReport(3L, 3L);
        createReport(3L, 4L);
        createReport(4L, 1L);
        createReport(4L, 2L);
        createReport(4L, 3L);
        createReport(4L, 4L);
    }

    private void createReport(long patientId, long laborantId) {
        for (int i = 0; i < 200; i++) {
            reportService.createReport(new CreateReportRequest(
                    "Sinüzit",
                    "Soğuk Algınlığı",
                    patientId,
                    laborantId));
        }
    }
}
