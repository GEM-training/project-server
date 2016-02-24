package com.gem.nrserver.restful.dto;

import org.springframework.http.HttpStatus;

/**
 * Created by quanda on 19/02/2016.
 */
public class ResponseDTO {
    HttpStatus statusCode;
    String message;
    Object returnObject;

    public HttpStatus getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(HttpStatus statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getReturnObject() {
        return returnObject;
    }

    public void setReturnObject(Object returnObject) {
        this.returnObject = returnObject;
    }

    public ResponseDTO(HttpStatus statusCode, String message, Object returnObject) {

        this.statusCode = statusCode;
        this.message = message;
        this.returnObject = returnObject;
    }
}
