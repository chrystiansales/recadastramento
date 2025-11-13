package com.ccm.recadastramento.exception;

/**
 * Exception lançada quando há tentativa de criar recurso duplicado
 */
public class DuplicateResourceException extends RuntimeException {

    public DuplicateResourceException(String message) {
        super(message);
    }
}
