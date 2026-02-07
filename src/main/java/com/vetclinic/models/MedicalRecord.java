package com.vetclinic.models;

public class MedicalRecord {
    private final String id = java.util.UUID.randomUUID().toString();
    private final String patientId;
    private final String diagnosis;

    public MedicalRecord(String patientId, String diagnosis) {
        this.patientId = patientId;
        this.diagnosis = diagnosis;
    }

    public String getId() { return id; }
    public String getPatientId() { return patientId; }
    public String getDiagnosis() { return diagnosis; }
}
