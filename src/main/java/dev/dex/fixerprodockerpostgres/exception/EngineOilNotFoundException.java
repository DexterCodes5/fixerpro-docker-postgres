package dev.dex.fixerprodockerpostgres.exception;

public class EngineOilNotFoundException extends RuntimeException {
    public EngineOilNotFoundException(String message) {
        super(message);
    }

    public EngineOilNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public EngineOilNotFoundException(Throwable cause) {
        super(cause);
    }
}
