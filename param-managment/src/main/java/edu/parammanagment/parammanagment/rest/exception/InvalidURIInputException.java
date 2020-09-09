package edu.parammanagment.parammanagment.rest.exception;

public class InvalidURIInputException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public InvalidURIInputException() {
    }

    public InvalidURIInputException(String message) {
        super(message);
    }
}
