package com.branch.exercise.error;

public class MalformedUrlException extends RuntimeException {
    public MalformedUrlException(String message) {
       super(message);
    }
}
