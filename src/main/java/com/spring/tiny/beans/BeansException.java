package com.spring.tiny.beans;

public class BeansException extends RuntimeException {
    public BeansException() {}
    public BeansException(String message) {
        super(message);
    }
    public BeansException(String message, Throwable cause) {
        super(message, cause);
    }
    public BeansException(Throwable cause) {
        super(cause);
    }
}
