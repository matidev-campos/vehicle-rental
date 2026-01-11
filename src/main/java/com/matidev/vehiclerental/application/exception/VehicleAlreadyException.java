package com.matidev.vehiclerental.application.exception;

public class VehicleAlreadyException extends RuntimeException{

    public VehicleAlreadyException(String vehicleId) {
        super("Vehicle already rented: " + vehicleId);
    }
}
