package com.matidev.vehiclerental.application.usecase;

import com.matidev.vehiclerental.domain.model.Vehicle;
import com.matidev.vehiclerental.domain.repository.VehicleRepository;

public class CreateVehicleUseCase {

    private final VehicleRepository vehicleRepository;

    public CreateVehicleUseCase(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public Vehicle execute(String brand, String model){
        Vehicle vehicle = new Vehicle(brand, model);
        return vehicleRepository.save(vehicle);
    }
}
