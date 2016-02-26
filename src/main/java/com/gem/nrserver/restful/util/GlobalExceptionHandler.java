package com.gem.nrserver.restful.util;

import com.gem.nrserver.service.exception.ProductNotFoundException;
import com.gem.nrserver.service.exception.StoreNotFoundException;
import com.gem.nrserver.service.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "requested store not found")
    @ExceptionHandler(StoreNotFoundException.class)
    public void handleStoreNotFound() {

    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "requested product not found")
    @ExceptionHandler(ProductNotFoundException.class)
    public void handleProductNotFound() {

    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "requested user not found")
    @ExceptionHandler(UserNotFoundException.class)
    public void handleUserNotFound() {

    }


}
