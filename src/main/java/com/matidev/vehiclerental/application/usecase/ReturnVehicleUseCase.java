package com.matidev.vehiclerental.application.usecase;

import com.matidev.vehiclerental.domain.model.Vehicle;
import com.matidev.vehiclerental.domain.repository.VehicleRepository;

public class ReturnVehicleUseCase {

    private final VehicleRepository vehicleRepository;

    public ReturnVehicleUseCase(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    /**
     * Executes the vehicle return process.
     *
     * @param vehicleId identifier of the vehicle to be returned
     */
    public void execute(String vehicleId){
        Vehicle vehicle = vehicleRepository.findById(vehicleId).
                orElseThrow(()->new IllegalStateException("Vehicle not found"));

        vehicle.returnVehicle();
        vehicleRepository.save(vehicle);
    }
}
