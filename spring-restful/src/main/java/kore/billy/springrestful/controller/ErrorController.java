package kore.billy.springrestful.controller;

import jakarta.validation.ConstraintViolationException;
import kore.billy.springrestful.model.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class ErrorController {
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Response<String>> constraintViolationException(ConstraintViolationException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Response.<String>builder().errors(e.getMessage()).build());
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Response<String>> apiException(ResponseStatusException e) {
        return ResponseEntity.status(e.getStatusCode())
                .body(Response.<String>builder().errors(e.getReason()).build());
    }
}
