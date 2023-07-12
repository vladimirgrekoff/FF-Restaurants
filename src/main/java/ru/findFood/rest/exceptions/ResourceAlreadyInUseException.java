package ru.findFood.rest.exceptions;

public class ResourceAlreadyInUseException extends RuntimeException {


    public ResourceAlreadyInUseException(String message) {
        super(message);
    }
}
