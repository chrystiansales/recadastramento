package com.ccm.recadastramento.exception;

/**
 * Exception lançada quando um recurso não é encontrado
 */
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
