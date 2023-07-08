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

@SpringBootApplication
public class LaboratoryReportingApplication {

	public static void main(String[] args) {
		SpringApplication.run(LaboratoryReportingApplication.class, args);
	}

}
