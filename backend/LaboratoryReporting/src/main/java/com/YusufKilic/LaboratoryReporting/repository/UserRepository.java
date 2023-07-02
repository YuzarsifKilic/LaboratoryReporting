package com.YusufKilic.LaboratoryReporting.repository;

import com.YusufKilic.LaboratoryReporting.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
