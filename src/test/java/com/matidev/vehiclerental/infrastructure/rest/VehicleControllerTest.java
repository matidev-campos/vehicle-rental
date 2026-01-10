package com.matidev.vehiclerental.infrastructure.rest;

import com.matidev.vehiclerental.application.usecase.CreateVehicleUseCase;
import com.matidev.vehiclerental.application.usecase.GetAvailableVehiclesUseCase;
import com.matidev.vehiclerental.application.usecase.RentVehicleUseCase;
import com.matidev.vehiclerental.application.usecase.ReturnVehicleUseCase;
import com.matidev.vehiclerental.infrastructure.controller.VehicleController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = VehicleController.class)
@AutoConfigureMockMvc(addFilters = false)
class VehicleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private RentVehicleUseCase rentVehicleUseCase;

    @MockitoBean
    private ReturnVehicleUseCase returnVehicleUseCase;

    @MockitoBean
    private CreateVehicleUseCase createVehicleUseCase;

    @MockitoBean
    private GetAvailableVehiclesUseCase getAvailableVehiclesUseCase;

    @Test
    void shouldRentVehicle() throws Exception {
        String vehicleId = "123";

        mockMvc.perform(post("/api/v1/vehicles/{id}/rent", vehicleId))
                .andExpect(status().isOk());

        verify(rentVehicleUseCase).execute(vehicleId);
    }
}

