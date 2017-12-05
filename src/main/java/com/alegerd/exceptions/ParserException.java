package com.alegerd.exceptions;

public class ParserException extends Exception {

    String message;

    public  ParserException(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
