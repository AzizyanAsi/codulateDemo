package com.example.demo.exception;

public class RoleNotFoundException extends RuntimeException {

    private final String message;

    private final Object data;

    public RoleNotFoundException(String message, Object data) {
        super(message);
        this.message = message;
        this.data = data;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }
}
