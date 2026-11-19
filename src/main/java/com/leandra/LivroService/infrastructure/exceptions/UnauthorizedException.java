package com.leandra.LivroService.infrastructure.exceptions;

import javax.naming.AuthenticationException;

public class UnauthorizedException extends RuntimeException {

    public UnauthorizedException(String message) {
        super(message);
    }

    public UnauthorizedException(String message, Throwable cause) {
        super(message,cause);

    }
}
