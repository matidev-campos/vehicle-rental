package com.matidev.vehiclerental.domain.repository;

import com.matidev.vehiclerental.domain.model.Vehicle;

import java.util.List;
import java.util.Optional;

public interface VehicleRepository {

    Vehicle save(Vehicle vehicle);

    Optional<Vehicle> findById(String id);

    List<Vehicle> findAvailable();

}
