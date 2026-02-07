package com.vetclinic.repository;

import com.vetclinic.models.MedicalRecord;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MedicalRecordRepository {

    private final List<MedicalRecord> records = new ArrayList<>();

    public List<MedicalRecord> findAll() {
        return records;
    }

    public List<MedicalRecord> findByPatientId(String patientId) {
        List<MedicalRecord> result = new ArrayList<>();
        for (MedicalRecord r : records) {
            if (r.getPatientId().equals(patientId)) {
                result.add(r);
            }
        }
        return result;
    }

    public Optional<MedicalRecord> findById(String id) {
        return records.stream().filter(r -> r.getId().equals(id)).findFirst();
    }

    public void save(MedicalRecord record) {
        records.add(record);
    }

    public void delete(String id) {
        records.removeIf(r -> r.getId().equals(id));
    }
}
