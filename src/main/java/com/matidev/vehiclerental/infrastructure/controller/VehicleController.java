package com.matidev.vehiclerental.infrastructure.controller;

import com.matidev.vehiclerental.application.usecase.CreateVehicleUseCase;
import com.matidev.vehiclerental.application.usecase.GetAvailableVehiclesUseCase;
import com.matidev.vehiclerental.application.usecase.RentVehicleUseCase;
import com.matidev.vehiclerental.application.usecase.ReturnVehicleUseCase;
import com.matidev.vehiclerental.domain.model.Vehicle;
import com.matidev.vehiclerental.infrastructure.controller.dto.request.CreateVehicleRequest;
import com.matidev.vehiclerental.infrastructure.controller.dto.response.VehicleResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/vehicles")
public class VehicleController {

    private final RentVehicleUseCase rentVehicleUseCase;
    private final ReturnVehicleUseCase returnVehicleUseCase;
    private final CreateVehicleUseCase createVehicleUseCase;
    private final GetAvailableVehiclesUseCase getAvailableVehiclesUseCase;

    public VehicleController(RentVehicleUseCase rentVehicleUseCase,
                             ReturnVehicleUseCase returnVehicleUseCase,
                             CreateVehicleUseCase createVehicleUseCase,
                             GetAvailableVehiclesUseCase getAvailableVehiclesUseCase) {
        this.rentVehicleUseCase = rentVehicleUseCase;
        this.returnVehicleUseCase = returnVehicleUseCase;
        this.createVehicleUseCase = createVehicleUseCase;
        this.getAvailableVehiclesUseCase = getAvailableVehiclesUseCase;
    }

    @PostMapping
    public VehicleResponse create(@RequestBody CreateVehicleRequest request){
        Vehicle vehicle = createVehicleUseCase.execute(request.brand(), request.model());
        return toResponse(vehicle);
    }

    @GetMapping("/available")
    public List<VehicleResponse> getVehiclesAvailable(){
        return getAvailableVehiclesUseCase.execute().stream().map(this::toResponse).toList();
    }

    @PostMapping("{id}/rent")
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
