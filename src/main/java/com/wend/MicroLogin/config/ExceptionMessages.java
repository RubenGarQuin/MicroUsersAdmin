package com.wend.MicroLogin.config;

public enum ExceptionMessages {
    USER_NOT_FOUND("User not found"),
    EMAIL_ALREADY_USED("A user with that email already exists"),
    INCORRECT_PASSWORD("The current password is incorrect"),
    PASSWORD_CANNOT_MATCH("Passwords cannot match");

    private final String message;

    ExceptionMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
