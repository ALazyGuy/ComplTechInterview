package com.ltp.interview.exception;

public class LoginConflictException extends RuntimeException {

    public String getError() {
        return "User with same login already exists";
    }

}
