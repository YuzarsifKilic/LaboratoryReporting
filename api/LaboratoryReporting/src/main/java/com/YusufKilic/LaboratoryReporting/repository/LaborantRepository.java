package com.YusufKilic.LaboratoryReporting.repository;

import com.YusufKilic.LaboratoryReporting.model.Laborant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LaborantRepository extends JpaRepository<Laborant, Long> {
}
