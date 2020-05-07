package net.rom.api.research.exception;

public class ExoRuntimeException extends RuntimeException {
    public ExoRuntimeException() {
    }

    public ExoRuntimeException(String message) {
        super(message);
    }

    public ExoRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExoRuntimeException(Throwable cause) {
        super(cause);
    }

    public ExoRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
