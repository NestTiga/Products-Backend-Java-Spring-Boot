package com.tigasinestor.products.errors;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class PresentException extends Exception{
    private HttpStatus status;
    public PresentException(String message, HttpStatus status) {
        super(message);
        this.status=status;
    }
}
