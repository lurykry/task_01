package edu.parammanagment.parammanagment.rest.exception.exceptionhandler;

import edu.parammanagment.parammanagment.rest.exception.Error;
import edu.parammanagment.parammanagment.rest.exception.InvalidURIInputException;
import edu.parammanagment.parammanagment.rest.exception.ModelNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

/**
 * Handles  {@link ModelNotFoundException} and  {@link InvalidURIInputException} exceptions.
 * @author Kirill Mansurov
 * @version 1.0
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ResponseBody
    @ExceptionHandler(ModelNotFoundException.class)
    public ResponseEntity<Error> modelNotFoundHandler(ModelNotFoundException ex) {
        Error result = Error
                .builder()
                .description(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
    }

    @ResponseBody
    @ExceptionHandler(InvalidURIInputException.class)
    public ResponseEntity<Error> invalidUriInputHandler(InvalidURIInputException ex) {
        Error result = Error
                .builder()
                .description(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
    }
}