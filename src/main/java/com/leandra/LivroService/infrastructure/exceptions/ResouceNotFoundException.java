package com.leandra.LivroService.infrastructure.exceptions;

public class ResouceNotFoundException extends RuntimeException {
    public ResouceNotFoundException(String message) {
        super(message);
    }

  public ResouceNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }
}
