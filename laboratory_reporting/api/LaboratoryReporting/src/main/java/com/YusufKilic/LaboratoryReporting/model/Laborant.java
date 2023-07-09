package com.YusufKilic.LaboratoryReporting.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Laborant {

    @Id
    private Long id;
    private String firstName;
    private String lastName;
    @Column(length = 7)
    private String hospitalNumber;
    @OneToMany(mappedBy = "laborant")
    private Set<Report> report;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Laborant laborant = (Laborant) o;
        return Objects.equals(id, laborant.id) &&  Objects.equals(firstName, laborant.firstName) && Objects.equals(lastName, laborant.lastName) && Objects.equals(hospitalNumber, laborant.hospitalNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, hospitalNumber);
    }

    @Override
    public String toString() {
        return "Laborant{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", hospitalNumber='" + hospitalNumber + '\'' +
                '}';
    }
}