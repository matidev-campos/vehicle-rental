package com.matidev.vehiclerental.application.usecase;

import com.matidev.vehiclerental.domain.model.Vehicle;
import com.matidev.vehiclerental.domain.repository.VehicleRepository;

import java.util.List;

public class GetAvailableVehiclesUseCase {

    private final VehicleRepository vehicleRepository;

    public GetAvailableVehiclesUseCase(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public List<Vehicle> execute(){
        return vehicleRepository.findAvailable();
    }
}
