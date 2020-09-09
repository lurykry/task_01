package edu.parammanagment.parammanagment.rest.exception;

public class ModelNotFoundException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public ModelNotFoundException() {
    }

    public ModelNotFoundException(String message) {
        super(message);
    }

}