package com.matidev.vehiclerental.infrastructure.controller.dto.request;

public record CreateVehicleRequest(
        String brand,
        String model
) {
}
