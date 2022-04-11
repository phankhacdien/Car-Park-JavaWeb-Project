package com.sherlock.carpark.Exception;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String msg) {
        super(msg);
    }
}
