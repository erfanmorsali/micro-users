package com.example.microusers.modules.users.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class NotUniqueUsernameException extends Exception {

    @Serial
    private static final long serialVersionUID = 1L;

    public NotUniqueUsernameException(String message) {
        super(message);
    }
}




