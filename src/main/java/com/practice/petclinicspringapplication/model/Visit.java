package com.practice.petclinicspringapplication.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Visit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idVisit;
    private String reasonForVisit;
    private LocalDate dateOfVisit;
    @ManyToOne
    @JoinColumn(name="idOwner", nullable = false)
    private Owner owner;
    @OneToOne
    private Pet pet;
    @OneToOne
    private Vet vet;

    //Constructor
    public Visit() {
    }

    public Visit(String reasonForVisit, Owner owner, Pet pet, Vet vet) {
        this.reasonForVisit = reasonForVisit;
        this.owner = owner;
        this.pet = pet;
        this.vet = vet;
    }

    public Visit(String reasonForVisit, LocalDate dateOfVisit, Owner owner, Pet pet, Vet vet) {
        this.reasonForVisit = reasonForVisit;
        this.dateOfVisit = dateOfVisit;
        this.owner = owner;
        this.pet = pet;
        this.vet = vet;
    }

    //Getters and Setters
    public Long getIdVisit() {
        return idVisit;
    }

    public void setIdVisit(Long idVisit) {
        this.idVisit = idVisit;
    }

    public String getReasonForVisit() {
        return reasonForVisit;
    }

    public void setReasonForVisit(String reasonForVisit) {
        this.reasonForVisit = reasonForVisit;
    }

    public LocalDate getDateOfVisit() {
        return dateOfVisit;
    }

    public void setDateOfVisit(LocalDate dateOfVisit) {
        this.dateOfVisit = dateOfVisit;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
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

    //Equals and hashcode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Visit visit = (Visit) o;

        if (!Objects.equals(idVisit, visit.idVisit)) return false;
        return Objects.equals(dateOfVisit, visit.dateOfVisit);
    }

    @Override
    public int hashCode() {
        int result = idVisit != null ? idVisit.hashCode() : 0;
        result = 31 * result + (dateOfVisit != null ? dateOfVisit.hashCode() : 0);
        return result;
    }

    //toString
    @Override
    public String toString() {
        return "Visit{" +
                "idVisit=" + idVisit +
                ", reasonForVisit='" + reasonForVisit + '\'' +
                ", dateOfVisit=" + dateOfVisit +
                '}';
    }
}
