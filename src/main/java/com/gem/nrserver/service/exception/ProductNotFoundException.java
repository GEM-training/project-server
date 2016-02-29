package com.gem.nrserver.service.exception;

/**
 * Created by kimtung on 2/24/16.
 */
public class ProductNotFoundException extends ResourceNotFoundException {
    public ProductNotFoundException() {
    }

    public ProductNotFoundException(String message) {
        super(message);
    }
}
