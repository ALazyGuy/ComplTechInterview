package com.ltp.interview.exception;

public class UnknownGenderNameException extends RuntimeException {

    private final String name;

    public UnknownGenderNameException(final String name) {
        super(name);
        this.name = name;
    }

    public String getError() {
        return String.format("Error. Unknown gender name: %s", name);
    }

}
