package com.YusufKilic.LaboratoryReporting.exception;

import java.time.LocalDateTime;

public record ErrorMessage(
        String message,
        LocalDateTime timeStamp) {}
