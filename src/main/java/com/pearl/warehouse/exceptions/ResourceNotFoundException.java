package com.pearl.warehouse.exceptions;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String specializationNotFound) {
        super(specializationNotFound);
    }
}
