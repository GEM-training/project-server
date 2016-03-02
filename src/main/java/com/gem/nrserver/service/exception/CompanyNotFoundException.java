package com.gem.nrserver.service.exception;

/**
 * Created by qsoft on 2/29/16.
 */
public class CompanyNotFoundException extends ResourceNotFoundException {

    public CompanyNotFoundException() {

    }
    public CompanyNotFoundException(String message) {
        super(message);
    }

}
