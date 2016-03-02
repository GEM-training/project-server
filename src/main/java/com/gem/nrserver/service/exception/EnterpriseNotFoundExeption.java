package com.gem.nrserver.service.exception;

/**
 * Created by qsoft on 3/2/16.
 */
public class EnterpriseNotFoundExeption extends ResourceNotFoundException  {
    public EnterpriseNotFoundExeption() {

    }

    public EnterpriseNotFoundExeption(String message) {
        super(message);
    }
}
