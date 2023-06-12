package dev.dex.fixerprodockerpostgres.exception;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(String message) {
        super(message);
    }

    public ProductNotFoundException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public ProductNotFoundException(Throwable throwable) {
        super(throwable);
    }
}
