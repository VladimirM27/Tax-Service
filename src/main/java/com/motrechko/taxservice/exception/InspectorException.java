package com.motrechko.taxservice.exception;

public class InspectorException extends Exception{

    public InspectorException(String message) {
        super(message);
    }

    public InspectorException(String message, Throwable cause) {
        super(message, cause);
    }
}
