package com.vetclinic.models;

public class Patient {
    private final String id = java.util.UUID.randomUUID().toString();
    private final String name;
    private final String species;
    private final String breed;
    private final String ownerName;

    public Patient(String name, String species, String breed, String ownerName) {
        this.name = name;
        this.species = species;
        this.breed = breed;
        this.ownerName = ownerName;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getSpecies() { return species; }
    public String getBreed() { return breed; }
    public String getOwnerName() { return ownerName; }
}
