package com.vetclinic.utils;

import com.vetclinic.models.Appointment;
import com.vetclinic.models.MedicalRecord;
import com.vetclinic.models.Patient;

import java.util.List;

public class JsonSerializer {

    public static String serialize(Patient p) {
        if (p == null) return "{}";

        return "{"
                + "\"id\":\"" + p.getId() + "\","
                + "\"name\":\"" + escape(p.getName()) + "\","
                + "\"species\":\"" + escape(p.getSpecies()) + "\","
                + "\"breed\":\"" + escape(p.getBreed()) + "\","
                + "\"ownerName\":\"" + escape(p.getOwnerName()) + "\""
                + "}";
    }

    public static String serializePatients(List<Patient> patients) {
        return serializeList(patients, JsonSerializer::serialize);
    }

    public static String serialize(Appointment a) {
        if (a == null) return "{}";

        return "{"
                + "\"id\":\"" + a.getId() + "\","
                + "\"patientId\":\"" + a.getPatientId() + "\","
                + "\"doctorName\":\"" + escape(a.getDoctorName()) + "\""
                + "}";
    }

    public static String serializeAppointments(List<Appointment> appointments) {
        return serializeList(appointments, JsonSerializer::serialize);
    }

    public static String serialize(MedicalRecord r) {
        if (r == null) return "{}";

        return "{"
                + "\"id\":\"" + r.getId() + "\","
                + "\"patientId\":\"" + r.getPatientId() + "\","
                + "\"diagnosis\":\"" + escape(r.getDiagnosis()) + "\""
                + "}";
    }

    public static String serializeMedicalRecords(List<MedicalRecord> records) {
        return serializeList(records, JsonSerializer::serialize);
    }

    private static String escape(String s) {
        return s == null ? "" : s.replace("\"", "\\\"");
    }

    private interface Serializer<T> {
        String serialize(T t);
    }

    private static <T> String serializeList(List<T> list, Serializer<T> serializer) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < list.size(); i++) {
            sb.append(serializer.serialize(list.get(i)));
            if (i < list.size() - 1) sb.append(",");
        }
        sb.append("]");
        return sb.toString();
    }
}
