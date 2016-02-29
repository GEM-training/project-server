package com.gem.nrserver.service.exception;


public class InvoiceNotFoundException extends ResourceNotFoundException {

    public InvoiceNotFoundException() {
    }

    public InvoiceNotFoundException(String message) {
        super(message);
    }
}
