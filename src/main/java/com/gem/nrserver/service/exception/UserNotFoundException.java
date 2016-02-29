package com.gem.nrserver.service.exception;

/**
 * Created by kimtung on 2/26/16.
 */
public class UserNotFoundException extends ResourceNotFoundException {
    public UserNotFoundException() {
    }

    public UserNotFoundException(String message) {
        super(message);
    }
}
