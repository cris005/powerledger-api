package com.powerledger.api.exception;

public class ResourceNotFoundException  extends Exception {
    // Parameterless Constructor
    public ResourceNotFoundException() {}

    // Constructor that accepts a message
    public ResourceNotFoundException(String message)
    {
        super(message);
    }
}