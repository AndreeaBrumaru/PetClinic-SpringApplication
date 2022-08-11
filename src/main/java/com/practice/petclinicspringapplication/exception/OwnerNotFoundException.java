package com.practice.petclinicspringapplication.exception;

public class OwnerNotFoundException extends RuntimeException{
    public OwnerNotFoundException(Long id)
    {
        super(String.format("Owner with Id %d not found", id));
    }
}
