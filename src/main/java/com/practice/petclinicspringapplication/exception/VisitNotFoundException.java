package com.practice.petclinicspringapplication.exception;

public class VisitNotFoundException extends RuntimeException{
    public VisitNotFoundException(Long id) {
        super(String.format("Visit with Id %d not found", id));
    }
}
