package com.motrechko.taxservice.exception;

/**
 Custom exception class for handling SQLExceptions in the Tax Service application.
 */
public class MySQLException extends Exception {
    /**
     Constructs a new MySQLException with the specified detail message and cause.
     @param message detailed exception message
     @param cause cause of exception
     */
    public MySQLException(String message, Throwable cause){
        super(message,cause);
    }
    /**
     Constructs a new MySQLException with the specified detail message.
     @param message detailed exception message
     */
    public MySQLException(String message){
        super(message);
    }
}