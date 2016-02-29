package com.gem.nrserver.restful.util;

import com.gem.nrserver.service.exception.ProductNotFoundException;
import com.gem.nrserver.service.exception.StoreNotFoundException;
import com.gem.nrserver.service.exception.UserNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "requested store not found")
    @ExceptionHandler(StoreNotFoundException.class)
    public void handleStoreNotFound(StoreNotFoundException e) {
        LOGGER.debug(e.getMessage());
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "requested product not found")
    @ExceptionHandler(ProductNotFoundException.class)
    public void handleProductNotFound(ProductNotFoundException e) {
        LOGGER.debug(e.getMessage());
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "requested user not found")
    @ExceptionHandler(UserNotFoundException.class)
    public void handleUserNotFound(UserNotFoundException e) {
        LOGGER.debug(e.getMessage());
    }


}
