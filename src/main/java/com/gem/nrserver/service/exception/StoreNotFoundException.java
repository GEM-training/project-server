package com.gem.nrserver.service.exception;

/**
 * Created by kimtung on 2/24/16.
 */
public class StoreNotFoundException extends ResourceNotFoundException {
    public StoreNotFoundException() {
    }

    public StoreNotFoundException(String message) {
        super(message);
    }
}
