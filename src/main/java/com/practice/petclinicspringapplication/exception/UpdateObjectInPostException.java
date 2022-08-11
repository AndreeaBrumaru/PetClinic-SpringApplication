package com.practice.petclinicspringapplication.exception;

public class UpdateObjectInPostException extends RuntimeException{
    public UpdateObjectInPostException() {
        super("Update method called on Post. Please switch to Put.");
    }
}
