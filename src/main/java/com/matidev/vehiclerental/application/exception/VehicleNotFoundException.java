package com.matidev.vehiclerental.application.exception;

public class VehicleNotFoundException extends RuntimeException{

    public VehicleNotFoundException(String vehicleId){
        super("Vehicle not found with id: " + vehicleId);
    }
}
