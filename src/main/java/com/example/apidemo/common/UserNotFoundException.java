package com.example.apidemo.common;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(int id) {
        super("Could not find user " + id);
    }
}
