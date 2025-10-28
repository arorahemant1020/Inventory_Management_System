package com.ncu.edu.authservice.exceptions;

public class DatabaseException extends RuntimeException {
    public DatabaseException(String msg, Throwable cause) { super(msg, cause); }
    public DatabaseException(String msg) { super(msg); }
}
