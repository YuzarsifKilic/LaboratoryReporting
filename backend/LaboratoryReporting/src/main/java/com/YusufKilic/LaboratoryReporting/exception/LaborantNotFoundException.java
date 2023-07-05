package com.YusufKilic.LaboratoryReporting.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class LaborantNotFoundException extends RuntimeException {

    public LaborantNotFoundException(String message) {
        super(message);
    }
}
