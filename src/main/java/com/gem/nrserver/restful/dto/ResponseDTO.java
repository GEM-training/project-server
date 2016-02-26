package com.gem.nrserver.restful.dto;

/**
 * Created by quanda on 19/02/2016.
 */
public class ResponseDTO {
    private Integer statusCode;
    private String message;
    private Object returnObject;

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
