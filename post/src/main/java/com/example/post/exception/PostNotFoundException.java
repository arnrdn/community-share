package com.example.post.exception;

public class PostNotFoundException extends Exception{

    public PostNotFoundException (String errorMsg) {
        super(errorMsg);
    }
}
