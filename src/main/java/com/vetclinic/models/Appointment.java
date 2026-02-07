package com.vetclinic.models;

public class Appointment {
    private final String id = java.util.UUID.randomUUID().toString();
    private final String patientId;
    private final String doctorName;

    public Appointment(String patientId, String doctorName) {
        this.patientId = patientId;
        this.doctorName = doctorName;
    }

    public String getId() { return id; }
    public String getPatientId() { return patientId; }
    public String getDoctorName() { return doctorName; }
}
