package com.matidev.vehiclerental.infrastructure.persistence.mapper;

import com.matidev.vehiclerental.domain.model.Vehicle;
import com.matidev.vehiclerental.infrastructure.persistence.document.VehicleDocument;

public class VehicleMapper {

    public VehicleMapper() {
    }

    public static VehicleDocument toDocument(Vehicle vehicle){
        VehicleDocument vehicleDocument = new VehicleDocument();
        vehicleDocument.setId(vehicle.getId());
        vehicleDocument.setBrand(vehicleDocument.getBrand());
        vehicleDocument.setModel(vehicleDocument.getModel());
        vehicleDocument.setAvailable(vehicle.isAvailable());
        return vehicleDocument;
    }

    public static Vehicle toDomain(VehicleDocument vehicleDocument){
        Vehicle vehicle = new Vehicle(vehicleDocument.getBrand(), vehicleDocument.getModel());

        if(!vehicle.isAvailable()){
            vehicle.rented();
        }

        return vehicle;
    }
}
