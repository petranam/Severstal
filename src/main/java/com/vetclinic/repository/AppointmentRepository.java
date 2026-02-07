package com.vetclinic.repository;

import com.vetclinic.models.Appointment;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AppointmentRepository {

    private final List<Appointment> appointments = new ArrayList<>();

    public List<Appointment> findAll() {
        return appointments;
    }

    public List<Appointment> findByPatientId(String patientId) {
        List<Appointment> result = new ArrayList<>();
        for (Appointment a : appointments) {
            if (a.getPatientId().equals(patientId)) {
                result.add(a);
            }
        }
        return result;
    }

    public Optional<Appointment> findById(String id) {
        return appointments.stream()
                .filter(a -> a.getId().equals(id))
                .findFirst();
    }

    public void save(Appointment appointment) {
        appointments.add(appointment);
    }

    public void delete(String id) {
        appointments.removeIf(a -> a.getId().equals(id));
    }
}
