package com.sapient.exception;

public class TicketBookingException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public TicketBookingException(String exception) {
        super(exception);
    }
}
