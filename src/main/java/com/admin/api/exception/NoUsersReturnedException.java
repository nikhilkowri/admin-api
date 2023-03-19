package com.admin.api.exception;

public class NoUsersReturnedException extends RuntimeException {

    public NoUsersReturnedException(String message) {
        super(message);
    }

    public NoUsersReturnedException() {
        super();
    }
}
