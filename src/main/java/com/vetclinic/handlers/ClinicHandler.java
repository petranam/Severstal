package com.vetclinic.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.vetclinic.models.*;
import com.vetclinic.repository.*;
import com.vetclinic.utils.*;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class ClinicHandler implements HttpHandler {

    private final PatientRepository patients = new PatientRepository();
    private final AppointmentRepository appointments = new AppointmentRepository();
    private final MedicalRecordRepository records = new MedicalRecordRepository();

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();
        String path = exchange.getRequestURI().getPath();

        if (path.equals("/patients")) {
            if (method.equals("GET")) {
                sendJson(exchange,
                        JsonSerializer.serializePatients(patients.findAll()));
                return;
            }
            if (method.equals("POST")) {
                Patient p = JsonParser.parsePatient(readBody(exchange));
                patients.save(p);
                sendJson(exchange, JsonSerializer.serialize(p));
                return;
            }
        }

        if (path.equals("/appointments")) {
            if (method.equals("GET")) {
                sendJson(exchange,
                        JsonSerializer.serializeAppointments(appointments.findAll()));
                return;
            }
            if (method.equals("POST")) {
                Appointment a = JsonParser.parseAppointment(readBody(exchange));
                appointments.save(a);
                sendJson(exchange, JsonSerializer.serialize(a));
                return;
            }
        }

        if (path.equals("/records")) {
            if (method.equals("GET")) {
                sendJson(exchange,
                        JsonSerializer.serializeMedicalRecords(records.findAll()));
                return;
            }
            if (method.equals("POST")) {
                MedicalRecord r = JsonParser.parseMedicalRecord(readBody(exchange));
                records.save(r);
                sendJson(exchange, JsonSerializer.serialize(r));
                return;
            }
        }

        exchange.sendResponseHeaders(404, -1);
        exchange.close();
    }

    private void sendJson(HttpExchange ex, String json) throws IOException {
        ex.getResponseHeaders().add("Content-Type", "application/json");
        byte[] data = json.getBytes(StandardCharsets.UTF_8);
        ex.sendResponseHeaders(200, data.length);
        try (OutputStream os = ex.getResponseBody()) {
            os.write(data);
        }
    }

    private String readBody(HttpExchange ex) throws IOException {
        try (InputStream is = ex.getRequestBody();
             ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            is.transferTo(out);
            return out.toString(StandardCharsets.UTF_8);
        }
    }
}
