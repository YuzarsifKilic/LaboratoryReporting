package com.YusufKilic.LaboratoryReporting.repository;

import com.YusufKilic.LaboratoryReporting.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report, Long> {

  @Query(
     "select r from Report r inner join Laborant l on r.laborant.id = l.id where l.firstName = :firstName and l.lastName = :lastName")
    List<Report> findReportsByLaborant(@Param("firstName") String firstName, @Param("lastName") String lastName);

    @Query(
            "select r from Report r inner join Patient p on r.patient.id = p.id where p.firstName = :firstName and p.lastName = :lastName")
    List<Report> findReportsByPatient(@Param("firstName") String firstName, @Param("lastName") String lastName);
}
