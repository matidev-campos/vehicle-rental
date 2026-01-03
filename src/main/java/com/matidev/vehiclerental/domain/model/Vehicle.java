package com.matidev.vehiclerental.domain.model;

import java.util.UUID;

public class Vehicle {

    private final String id;
    private final String brand;
    private final String model;
    private Boolean available;

    public Vehicle(String brand, String model){
        this.id = UUID.randomUUID().toString();
        this.brand = brand;
        this.model = model;
        this.available = true;
    }

    /**
     * Marks the vehicle as rented.
     *
     * @throws IllegalStateException if the vehicle is already rented
     */
    public void rented(){
        if(!available){
            throw new IllegalStateException("Vehicle is already rented");
        }
        this.available = false;
    }

    public void returnVehicle(){
        this.available = true;
    }

    public String getId() {
        return id;
    }

    public boolean isAvailable() {
        return available;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }
}
