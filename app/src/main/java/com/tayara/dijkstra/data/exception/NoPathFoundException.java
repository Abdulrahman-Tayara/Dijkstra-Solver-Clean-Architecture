package com.tayara.dijkstra.data.exception;

public class NoPathFoundException extends Exception {
    public NoPathFoundException() {
    }

    public NoPathFoundException(String message) {
        super(message);
    }

    public NoPathFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoPathFoundException(Throwable cause) {
        super(cause);
    }

    public NoPathFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
