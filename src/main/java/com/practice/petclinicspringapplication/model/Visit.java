package com.practice.petclinicspringapplication.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "visits")
public class Visit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "visitId")
    private Long id;
    private String reasonForVisit;
    private LocalDate dateOfVisit;
    //TODO REfactor so that we don't need owner anymore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "owner_id", referencedColumnName = "ownerId")
    private Owner owner;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pet_id", referencedColumnName = "petId")
    private Pet pet;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "vet_id", referencedColumnName = "vetId")
    private Vet vet;

    //Constructor
    public Visit(String reasonForVisit, LocalDate dateOfVisit, Owner owner, Pet pet, Vet vet) {
        this.reasonForVisit = reasonForVisit;
        this.dateOfVisit = dateOfVisit;
        this.owner = owner;
        this.pet = pet;
        this.vet = vet;
    }

    public Visit() {

    }

    //Getters and Setters
    public Long getIdVisit() {
        return id;
    }

    public String getReasonForVisit() {
        return reasonForVisit;
    }

    public LocalDate getDateOfVisit() {
        return dateOfVisit;
    }

    public Owner getOwner() {
        return owner;
    }

    public Pet getPet() {
        return pet;
    }

    public Vet getVet() {
        return vet;
    }

    public void setIdVisit(Long idVisit) {
        this.id = idVisit;
    }

    public void setReasonForVisit(String reasonForVisit) {
        this.reasonForVisit = reasonForVisit;
    }

    public void setDateOfVisit(LocalDate dateOfVisit) {
        this.dateOfVisit = dateOfVisit;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
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

        if (!Objects.equals(id, visit.id)) return false;
        return Objects.equals(dateOfVisit, visit.dateOfVisit);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (dateOfVisit != null ? dateOfVisit.hashCode() : 0);
        return result;
    }

    //toString
    @Override
    public String toString() {
        return "Visit{" +
                "idVisit=" + id +
                ", reasonForVisit='" + reasonForVisit + '\'' +
                ", dateOfVisit=" + dateOfVisit +
                '}';
    }
}
