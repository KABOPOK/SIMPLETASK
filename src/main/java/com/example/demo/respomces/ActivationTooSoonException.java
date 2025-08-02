package com.example.demo.respomces;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class ActivationTooSoonException extends RuntimeException {
    public ActivationTooSoonException(String message) {
        super(message);
    }
}
