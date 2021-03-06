package com.example.microusers.modules.users.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UserNotFoundException extends Exception {


    @Serial
    private static final long serialVersionUID = 2L;

    public UserNotFoundException(String message) {
        super(message);
    }
}


