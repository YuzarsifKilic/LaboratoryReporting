package com.YusufKilic.LaboratoryReporting;

import com.YusufKilic.LaboratoryReporting.model.*;
import com.YusufKilic.LaboratoryReporting.repository.LaborantRepository;
import com.YusufKilic.LaboratoryReporting.repository.PatientRepository;
import com.YusufKilic.LaboratoryReporting.repository.ReportRepository;
import com.YusufKilic.LaboratoryReporting.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class LaboratoryReportingApplication {

	public static void main(String[] args) {
		SpringApplication.run(LaboratoryReportingApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(UserRepository userRepository,
										LaborantRepository laborantRepository,
										PatientRepository patientRepository,
										ReportRepository reportRepository) {
		return args -> {
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

			Report report1 = Report.builder()
					.diagnosisHeader("Nezle")
					.diagnosisDescription("Soğuk Algınlığı")
					.reportDate(LocalDateTime.now())
					.laborant(laborant)
					.patient(patient)
					.build();

			reportRepository.save(report1);

			Report report2 = Report.builder()
					.diagnosisHeader("Kulak İltihabı")
					.diagnosisDescription("Soğuk Algınlığı")
					.reportDate(LocalDateTime.now())
					.laborant(laborant)
					.patient(patient)
					.build();
			reportRepository.save(report2);

			Report report3 = Report.builder()
					.diagnosisHeader("Kuru Öksürük")
					.diagnosisDescription("Soğuk Algınlığı")
					.reportDate(LocalDateTime.now())
					.laborant(laborant)
					.patient(patient)
					.build();
			reportRepository.save(report3);

			Report report4 = Report.builder()
					.diagnosisHeader("Nezle")
					.diagnosisDescription("Soğuk Algınlığı")
					.reportDate(LocalDateTime.now())
					.laborant(laborant2)
					.patient(patient2)
					.build();

			reportRepository.save(report4);
		};
	}

}
