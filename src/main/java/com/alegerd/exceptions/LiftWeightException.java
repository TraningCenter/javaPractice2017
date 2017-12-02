package com.alegerd.exceptions;

public class LiftWeightException extends Exception{
    String message;

    public  LiftWeightException(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
