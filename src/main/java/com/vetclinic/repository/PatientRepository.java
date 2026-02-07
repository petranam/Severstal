package com.vetclinic.repository;

import com.vetclinic.models.Patient;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PatientRepository {

    private final List<Patient> patients = new ArrayList<>();

    public List<Patient> findAll() {
        return patients;
    }

    public Optional<Patient> findById(String id) {
        return patients.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    }

    public void save(Patient patient) {
        patients.add(patient);
    }

    public void delete(String id) {
        patients.removeIf(p -> p.getId().equals(id));
    }
}
