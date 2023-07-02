package com.YusufKilic.LaboratoryReporting;

import com.YusufKilic.LaboratoryReporting.model.User;
import com.YusufKilic.LaboratoryReporting.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LaboratoryReportingApplication {

	public static void main(String[] args) {
		SpringApplication.run(LaboratoryReportingApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(UserRepository repository) {
		return args -> {
			repository.save(new User(1, "yuzarsif", "yuzarsif"));
		};
	}

}
