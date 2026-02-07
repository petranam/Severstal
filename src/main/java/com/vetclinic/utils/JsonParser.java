package com.vetclinic.utils;

import com.vetclinic.models.Appointment;
import com.vetclinic.models.MedicalRecord;
import com.vetclinic.models.Patient;

import java.util.HashMap;
import java.util.Map;

public class JsonParser {

    private static Map<String, String> parse(String json) {
        Map<String, String> map = new HashMap<>();
        if (json == null || json.isEmpty()) return map;

        json = json.trim();
        if (json.startsWith("{")) json = json.substring(1);
        if (json.endsWith("}")) json = json.substring(0, json.length() - 1);

        for (String pair : json.split(",")) {
            String[] kv = pair.split(":", 2);
            if (kv.length == 2) {
                map.put(clean(kv[0]), clean(kv[1]));
            }
        }
        return map;
    }

    private static String clean(String s) {
        return s.trim().replace("\"", "");
    }

    public static Patient parsePatient(String json) {
        Map<String, String> m = parse(json);
        return new Patient(
                m.getOrDefault("name", "Unknown"),
                m.getOrDefault("species", "Unknown"),
                m.getOrDefault("breed", "Unknown"),
                m.getOrDefault("ownerName", "Unknown")
        );
    }

    public static Appointment parseAppointment(String json) {
        Map<String, String> m = parse(json);
        return new Appointment(
                m.get("patientId"),
                m.getOrDefault("doctorName", "Unknown")
        );
    }

    public static MedicalRecord parseMedicalRecord(String json) {
        Map<String, String> m = parse(json);
        return new MedicalRecord(
                m.get("patientId"),
                m.getOrDefault("diagnosis", "Unknown")
        );
    }
}
