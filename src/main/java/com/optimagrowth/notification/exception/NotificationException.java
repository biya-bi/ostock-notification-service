package com.optimagrowth.notification.exception;

public class NotificationException extends RuntimeException {

    public NotificationException(String message) {
        super(message);
    }

    public NotificationException(Throwable throwable) {
        super(throwable);
    }

    public NotificationException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
