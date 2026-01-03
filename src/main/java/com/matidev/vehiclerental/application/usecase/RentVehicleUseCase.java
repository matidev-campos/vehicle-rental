package com.matidev.vehiclerental.application.usecase;

import com.matidev.vehiclerental.domain.model.Vehicle;
import com.matidev.vehiclerental.domain.repository.VehicleRepository;

public class RentVehicleUseCase {

    private final VehicleRepository vehicleRepository;

    public RentVehicleUseCase(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    /**
     * Executes the vehicle rental process.
     *
     * @param vehicleId identifier of the vehicle to be rented
     */
    public void execute(String vehicleId){
        Vehicle vehicle = vehicleRepository.findById(vehicleId).
                orElseThrow(()->new IllegalStateException("Vehicle not found"));

        vehicle.rented();
        vehicleRepository.save(vehicle);
    }
}
