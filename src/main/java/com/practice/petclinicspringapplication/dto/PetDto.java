package com.practice.petclinicspringapplication.dto;

import com.practice.petclinicspringapplication.model.Owner;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.Objects;

public class PetDto {
    private Long id;
    @NotBlank(message = "Name is mandatory.")
    private String namePet;
    @NotBlank(message = "Type is mandatory.")
    private String petType;
    private LocalDate birthDate;
    private Owner owner;

    //constructors
    public PetDto() {
    }

    public PetDto(Long id, String namePet, String petType, LocalDate birthDate, Owner owner) {
        this.id = id;
        this.namePet = namePet;
        this.petType = petType;
        this.birthDate = birthDate;
        this.owner = owner;
    }

    //getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNamePet() {
        return namePet;
    }

    public void setNamePet(String namePet) {
        this.namePet = namePet;
    }

    public String getPetType() {
        return petType;
    }

    public void setPetType(String petType) {
        this.petType = petType;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    //equals and hashcode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PetDto petDto = (PetDto) o;

        if (!Objects.equals(id, petDto.id)) return false;
        if (!Objects.equals(namePet, petDto.namePet)) return false;
        if (!Objects.equals(petType, petDto.petType)) return false;
        return Objects.equals(birthDate, petDto.birthDate);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (namePet != null ? namePet.hashCode() : 0);
        result = 31 * result + (petType != null ? petType.hashCode() : 0);
        result = 31 * result + (birthDate != null ? birthDate.hashCode() : 0);
        return result;
    }

    //toString
    @Override
    public String toString() {
        return "PetDto{" +
                "id=" + id +
                ", namePet='" + namePet + '\'' +
                ", petType='" + petType + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}
