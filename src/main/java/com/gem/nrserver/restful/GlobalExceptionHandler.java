package com.gem.nrserver.restful;

import com.gem.nrserver.service.exception.ProductNotFoundException;
import com.gem.nrserver.service.exception.StoreNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by kimtung on 2/24/16.
 */
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


}
