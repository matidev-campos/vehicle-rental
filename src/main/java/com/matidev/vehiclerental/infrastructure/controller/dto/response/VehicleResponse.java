package com.matidev.vehiclerental.infrastructure.controller.dto.response;

public record VehicleResponse(
        String id,
        String brand,
        String model,
        Boolean available
) {
}
