package com.ltp.interview.exception;

public class InvalidCredentialsException extends RuntimeException {
    public String getError() {
        return "Invalid credentials";
    }
}
