package com.practice.petclinicspringapplication.exception;

public class VetNotFoundException extends RuntimeException{
    public VetNotFoundException(Long id) {
        super(String.format("Vet with Id %d not found", id));
    }
}
