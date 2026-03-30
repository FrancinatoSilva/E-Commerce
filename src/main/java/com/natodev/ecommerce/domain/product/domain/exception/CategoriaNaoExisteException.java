package com.natodev.ecommerce.domain.product.domain.exception;

public class CategoriaNaoExisteException extends RuntimeException {
    public CategoriaNaoExisteException(String message) {
        super(message);
    }
}
