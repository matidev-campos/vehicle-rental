package com.matidev.vehiclerental.infrastructure.controller;

import com.matidev.vehiclerental.application.usecase.RentVehicleUseCase;
import com.matidev.vehiclerental.application.usecase.ReturnVehicleUseCase;
import com.matidev.vehiclerental.domain.model.Vehicle;
import com.matidev.vehiclerental.domain.repository.VehicleRepository;
import com.matidev.vehiclerental.infrastructure.controller.dto.request.CreateVehicleRequest;
import com.matidev.vehiclerental.infrastructure.controller.dto.response.VehicleResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/vehicles")
public class VehicleController {

    private final VehicleRepository vehicleRepository;
    private final RentVehicleUseCase rentVehicleUseCase;
    private final ReturnVehicleUseCase returnVehicleUseCase;

    public VehicleController(VehicleRepository vehicleRepository, RentVehicleUseCase rentVehicleUseCase, ReturnVehicleUseCase returnVehicleUseCase) {
        this.vehicleRepository = vehicleRepository;
        this.rentVehicleUseCase = rentVehicleUseCase;
        this.returnVehicleUseCase = returnVehicleUseCase;
    }

    @PostMapping
    public VehicleResponse create(@RequestBody CreateVehicleRequest request){
        Vehicle vehicle = new Vehicle(request.brand(), request.model());
        Vehicle saved = vehicleRepository.save(vehicle);

        return toResponse(saved);
    }

    @GetMapping("/available")
    public List<VehicleResponse> getVehiclesAvailable(){
        return vehicleRepository.findAvailable().stream().map(this::toResponse).toList();
    }

    @PostMapping("{id}}/rent")
    public void rent(@PathVariable String id){
        rentVehicleUseCase.execute(id);
    }

    @PostMapping("{id}/return")
    public void returnVehicle(@PathVariable String id){
        returnVehicleUseCase.execute(id);
    }

    private VehicleResponse toResponse(Vehicle vehicle){
        return new VehicleResponse(
                vehicle.getId(),
                vehicle.getBrand(),
                vehicle.getModel(),
                vehicle.isAvailable()
        );
    }
}
