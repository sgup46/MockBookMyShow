package com.sapient.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(value = TicketBookingException.class)
    public ResponseEntity<Object> exception(TicketBookingException exception) {
        return new ResponseEntity<>("Exception while ticket Booking: " + exception.getMessage(), HttpStatus.CONFLICT);

    }
}
