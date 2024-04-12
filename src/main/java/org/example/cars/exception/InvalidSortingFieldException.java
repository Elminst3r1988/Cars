package org.example.cars.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidSortingFieldException extends RuntimeException {
    public InvalidSortingFieldException(String message) {
        super(message);
    }
}