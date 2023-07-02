package com.YusufKilic.LaboratoryReporting.repository;

import com.YusufKilic.LaboratoryReporting.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}
