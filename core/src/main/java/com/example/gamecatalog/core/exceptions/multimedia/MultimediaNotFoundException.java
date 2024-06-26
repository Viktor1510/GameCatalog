package com.example.gamecatalog.core.exceptions.multimedia;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MultimediaNotFoundException extends RuntimeException {
    public MultimediaNotFoundException(String message) {
        super(message);
    }
}