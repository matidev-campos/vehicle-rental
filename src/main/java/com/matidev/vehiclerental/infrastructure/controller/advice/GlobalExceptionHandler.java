package com.matidev.vehiclerental.infrastructure.controller.advice;

import com.matidev.vehiclerental.application.exception.VehicleAlreadyException;
import com.matidev.vehiclerental.application.exception.VehicleNotFoundException;
import com.matidev.vehiclerental.infrastructure.controller.error.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(VehicleNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiError handleNotFound(VehicleNotFoundException ex){
        return ApiError.of("VEHICLE_NOT_FOUND", ex.getMessage());
    }

    @ExceptionHandler(VehicleAlreadyException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ApiError handleAlreadyRented(VehicleAlreadyException ex){
        return ApiError.of("VEHICLE_ALREADY_RENTED", ex.getMessage());
    }

}
