package com.rest.apidemo.dao.exceptions;

public class RTSQLIntegrityConstraintViolationException extends RuntimeException {
    public RTSQLIntegrityConstraintViolationException(String message) {
        super(message);
    }
}
