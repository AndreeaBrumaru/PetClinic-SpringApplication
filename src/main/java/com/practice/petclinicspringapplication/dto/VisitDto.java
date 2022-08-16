package com.practice.petclinicspringapplication.dto;

import com.practice.petclinicspringapplication.model.Pet;
import com.practice.petclinicspringapplication.model.Vet;

import java.time.LocalDate;

public class VisitDto {
    private Long id;
    private String reason;
    private LocalDate date;
    private Pet pet;
    private Vet vet;

    //getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public Vet getVet() {
        return vet;
    }

    public void setVet(Vet vet) {
        this.vet = vet;
    }
}
