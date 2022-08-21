package com.oscngl.customer.exception;

public class EntityAlreadyExistsException extends RuntimeException {

    public EntityAlreadyExistsException(String msg) {
        super(msg);
    }

}