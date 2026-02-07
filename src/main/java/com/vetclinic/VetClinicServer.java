package com.vetclinic;

import com.sun.net.httpserver.HttpServer;
import com.vetclinic.handlers.ClinicHandler;

import java.io.IOException;
import java.net.InetSocketAddress;

public class VetClinicServer {
    private static final int PORT = 8080;
    private static final int BACKLOG = 0;

    public static void main(String[] args) {
        try {
            int port = PORT;

            if (args.length > 0) {
                try {
                    port = Integer.parseInt(args[0]);
                } catch (NumberFormatException e) {
                    System.err.println("Invalid port number. Using default port: " + PORT);
                }
            }

            HttpServer server = HttpServer.create(new InetSocketAddress(port), BACKLOG);

            ClinicHandler clinicHandler = new ClinicHandler();

            server.createContext("/", clinicHandler);

            server.start();

            System.out.println("VetClinic HTTP Server Started on port " + port);

        } catch (IOException e) {
            System.err.println("Failed to start server: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }
}
