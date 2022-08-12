package com.practice.petclinicspringapplication.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "pets")
public class Pet{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "petId")
    private Long id;
    private String namePet;
    private String petType;
    private LocalDate birthDate;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "owner_id", referencedColumnName = "ownerId")
    private Owner owner;

    //Constructors
    public Pet(String namePet, LocalDate birthDate, String petType, Owner owner) {
        this.namePet = namePet;
        this.birthDate = birthDate;
        this.petType = petType;
        this.owner = owner;
    }

    public Pet(String namePet, String petType) {
        this.namePet = namePet;
        this.petType = petType;
    }

    public Pet() {

    }

    //Getters and Setters
    public Long getIdPet() {
        return id;
    }

    public String getNamePet() {
        return namePet;
    }

    public String getPetType() {
        return petType;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setIdPet(Long idPet) {
        this.id = idPet;
    }

    public void setNamePet(String namePet) {
        this.namePet = namePet;
    }

    public void setPetType(String petType) {
        this.petType = petType;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    //Equals and hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pet pet = (Pet) o;

        if (!Objects.equals(id, pet.id)) return false;
        if (!Objects.equals(namePet, pet.namePet)) return false;
        return Objects.equals(petType, pet.petType);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (namePet != null ? namePet.hashCode() : 0);
        result = 31 * result + (petType != null ? petType.hashCode() : 0);
        return result;
    }

    //toString
    @Override
    public String toString() {
        return "Pet{" +
                "idPet=" + id +
                ", namePet= '" + namePet + '\'' +
                ", petType= '" + petType + '\'' +
                ", birthDate= " + birthDate +
                '}';
    }
}
