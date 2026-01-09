package com.matidev.vehiclerental.infrastructure.config;

import com.matidev.vehiclerental.application.usecase.RentVehicleUseCase;
import com.matidev.vehiclerental.application.usecase.ReturnVehicleUseCase;
import com.matidev.vehiclerental.domain.repository.VehicleRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean
    public RentVehicleUseCase rentVehicleUseCase(VehicleRepository vehicleRepository){
        return new RentVehicleUseCase(vehicleRepository);
    }

    @Bean
    public ReturnVehicleUseCase returnVehicleUseCase(VehicleRepository vehicleRepository){
        return new ReturnVehicleUseCase(vehicleRepository);
    }
}
