package com.example.user.exception;

public class UserNotFoundException extends Exception{

    public UserNotFoundException(String errorMsg) {
        super(errorMsg);
    }

}
