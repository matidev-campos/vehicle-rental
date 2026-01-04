package com.matidev.vehiclerental.infrastructure.persistence.repository;

import com.matidev.vehiclerental.infrastructure.persistence.document.VehicleDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SpringDataVehicleRepository extends MongoRepository<VehicleDocument, String> {

    List<VehicleDocument> findByAvailableTrue();

}
