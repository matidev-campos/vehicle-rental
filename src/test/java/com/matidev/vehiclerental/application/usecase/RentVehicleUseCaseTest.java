package com.matidev.vehiclerental.application.usecase;

import com.matidev.vehiclerental.application.exception.VehicleNotFoundException;
import com.matidev.vehiclerental.domain.model.Vehicle;
import com.matidev.vehiclerental.domain.repository.VehicleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class RentVehicleUseCaseTest {


    private VehicleRepository vehicleRepository;
    private RentVehicleUseCase rentVehicleUseCase;

    @BeforeEach
    void setUp(){
        vehicleRepository = Mockito.mock(VehicleRepository.class);
        rentVehicleUseCase = new RentVehicleUseCase(vehicleRepository);
    }

    @Test
    void shouldRentAvailableVehicle() {

        //given
        Vehicle vehicle = new Vehicle("Toyota", "Corolla");

        when(vehicleRepository.findById(vehicle.getId()))
                .thenReturn(Optional.of(vehicle));

        //when
        rentVehicleUseCase.execute(vehicle.getId());

        //then
        assertFalse(vehicle.isAvailable());
        verify(vehicleRepository).save(vehicle);


    }

    @Test
    void shouldThrowExceptionWhenVehicleNotFound() {
        when(vehicleRepository.findById("non-existing-id"))
                .thenReturn(Optional.empty());

        assertThrows(VehicleNotFoundException.class,
                () -> rentVehicleUseCase.execute("non-existing-id"));
    }


    @Test
    void shouldNotRentAlreadyRentedVehicle() {
        Vehicle vehicle = new Vehicle("Ford", "Fiesta");
        vehicle.rented(); // cambia el estado

        when(vehicleRepository.findById(vehicle.getId()))
                .thenReturn(Optional.of(vehicle));

        assertThrows(IllegalStateException.class,
                () -> rentVehicleUseCase.execute(vehicle.getId()));
    }


}
