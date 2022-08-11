package com.practice.petclinicspringapplication.exception;

public class NoDataFoundException extends RuntimeException{
    public NoDataFoundException()
    {
        super("No data found");
    }
}
