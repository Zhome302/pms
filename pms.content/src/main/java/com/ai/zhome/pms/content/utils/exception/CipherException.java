package com.ai.zhome.pms.content.utils.exception;

public class CipherException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public CipherException(String message, Throwable cause) {
        super(message, cause);
    }

    public CipherException(String message) {
        super(message);
    }

    public CipherException(Throwable cause) {
        super(cause);
    }

}

