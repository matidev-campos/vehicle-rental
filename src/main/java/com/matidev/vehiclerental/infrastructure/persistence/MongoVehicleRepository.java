package com.matidev.vehiclerental.infrastructure.persistence;

import com.matidev.vehiclerental.domain.model.Vehicle;
import com.matidev.vehiclerental.domain.repository.VehicleRepository;
import com.matidev.vehiclerental.infrastructure.persistence.document.VehicleDocument;
import com.matidev.vehiclerental.infrastructure.persistence.mapper.VehicleMapper;
import com.matidev.vehiclerental.infrastructure.persistence.repository.SpringDataVehicleRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MongoVehicleRepository implements VehicleRepository {

    private final SpringDataVehicleRepository repository;

    public MongoVehicleRepository(SpringDataVehicleRepository repository) {
        this.repository = repository;
    }

    @Override
    public Vehicle save(Vehicle vehicle) {
        VehicleDocument saved = repository.save(VehicleMapper.toDocument(vehicle));
        return VehicleMapper.toDomain(saved);
    }

    @Override
    public Optional<Vehicle> findById(String id) {

        return repository.findById(id).map(VehicleMapper::toDomain);
    }

    @Override
    public List<Vehicle> findAvailable() {
        return repository.findByAvailableTrue().stream().map(VehicleMapper::toDomain).toList();
    }
}
