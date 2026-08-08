package com.pearl.warehouse.exceptions;

public class ResourceAlreadyExistsException extends RuntimeException {
    public ResourceAlreadyExistsException(String s) {
        super(s);
    }
}
