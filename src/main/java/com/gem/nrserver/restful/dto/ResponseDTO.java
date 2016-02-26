package com.gem.nrserver.restful.dto;

import com.sun.org.apache.regexp.internal.RE;
import org.springframework.http.HttpStatus;

/**
 * Created by quanda on 19/02/2016.
 */
public class ResponseDTO {
    Integer statusCode;
    String message;
    Object returnObject;

    public ResponseDTO() {}

    public ResponseDTO(int statusCode, String message, Object returnObject) {

        this.statusCode = statusCode;
        this.message = message;
        this.returnObject = returnObject;
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

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }
}
