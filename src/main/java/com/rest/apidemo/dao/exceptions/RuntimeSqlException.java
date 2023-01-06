package com.rest.apidemo.dao.exceptions;

public class RuntimeSqlException extends RuntimeException {

    public RuntimeSqlException(String message) {
        super(message);
    }
}
