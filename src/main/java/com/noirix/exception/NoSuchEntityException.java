package com.noirix.exception;

public class NoSuchEntityException extends RuntimeException {

    private String customMessage;

    private Integer errorCode;

    public NoSuchEntityException(String customMessage, Integer errorCode) {
        this.customMessage = customMessage;
        this.errorCode = errorCode;
    }

    public NoSuchEntityException(String message, String customMessage, Integer errorCode) {
        super(message);
        this.customMessage = customMessage;
        this.errorCode = errorCode;
    }

    public NoSuchEntityException(String message, Throwable cause, String customMessage, Integer errorCode) {
        super(message, cause);
        this.customMessage = customMessage;
        this.errorCode = errorCode;
    }

    public NoSuchEntityException(Throwable cause, String customMessage, Integer errorCode) {
        super(cause);
        this.customMessage = customMessage;
        this.errorCode = errorCode;
    }

    public NoSuchEntityException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String customMessage, Integer errorCode) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.customMessage = customMessage;
        this.errorCode = errorCode;
    }

    @Override
    public String toString() {
        return "NoSuchEntityException{" +
                "customMessage='" + customMessage + '\'' +
                ", errorCode=" + errorCode +
                "} " + super.toString();
    }
}
