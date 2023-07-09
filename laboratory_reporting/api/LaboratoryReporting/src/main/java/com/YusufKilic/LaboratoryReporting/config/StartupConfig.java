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

    public StartupConfig(UserService userService, LaborantRepository laborantRepository, PatientRepository patientRepository) {
        this.userService = userService;
        this.laborantRepository = laborantRepository;
        this.patientRepository = patientRepository;
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
    }
}
