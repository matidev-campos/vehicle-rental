package com.matidev.vehiclerental.infrastructure.controller.advice;

import com.matidev.vehiclerental.application.exception.VehicleNotFoundException;
import com.matidev.vehiclerental.application.usecase.RentVehicleUseCase;
import com.matidev.vehiclerental.infrastructure.controller.VehicleController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = VehicleController.class)
@AutoConfigureMockMvc(addFilters = false)
public class GlobalExceptionHandlerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private RentVehicleUseCase rentVehicleUseCase;

    void shouldReturn404WhenVehicleNotFound() throws Exception{

        String vehicleId = "123";

        doThrow(new VehicleNotFoundException(vehicleId))
                .when(rentVehicleUseCase)
                .execute(vehicleId);

        mockMvc.perform(post("/api/v1/vehicles/{id}/rent", vehicleId))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code").value("VEHICLE_NOT_FOUND"))
                .andExpect(jsonPath("$.message")
                .value("Vehicle not found with id: " + vehicleId))
                .andExpect(jsonPath("$.timestamp").exists());

    }


}
