package com.YusufKilic.LaboratoryReporting.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GeneralExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorMessage> handleAllException(Exception exception, WebRequest request) {
        ErrorMessage errorMessage = new ErrorMessage(
                exception.getMessage());
        return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<ErrorMessage> handleUsernameNotFoundException(Exception exception, WebRequest request) {
        ErrorMessage errorMessage = new ErrorMessage(
                exception.getMessage());
        return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ReportNotFoundException.class)
    public final ResponseEntity<ErrorMessage> handleReportNotFoundException(Exception exception, WebRequest request) {
        ErrorMessage errorMessage = new ErrorMessage(
                exception.getMessage());
        return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AuthenticationException.class)
    public final ResponseEntity<ErrorMessage> authenticationException(Exception exception, WebRequest request) {
        ErrorMessage errorMessage = new ErrorMessage(
                exception.getMessage());
        return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.NOT_FOUND);
    }
}
