package com.practice.petclinicspringapplication.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Vet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idVet;
    private String firstName;
    private String lastName;

    //Constructor
    public Vet(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Vet() {

    }

    //Getters and Setters
    public Long getIdVet() {
        return idVet;
    }

    public void setIdVet(Long idVet) {
        this.idVet = idVet;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    //Equals and hashcode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vet vet = (Vet) o;

        if (!Objects.equals(idVet, vet.idVet)) return false;
        if (!Objects.equals(firstName, vet.firstName)) return false;
        return Objects.equals(lastName, vet.lastName);
    }

    @Override
    public int hashCode() {
        int result = idVet != null ? idVet.hashCode() : 0;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        return result;
    }

    //toString
    @Override
    public String toString() {
        return "Vet{" +
                "idVet=" + idVet +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
