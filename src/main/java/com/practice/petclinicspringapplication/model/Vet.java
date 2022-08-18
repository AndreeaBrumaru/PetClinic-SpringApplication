package com.practice.petclinicspringapplication.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "vets")
public class Vet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "vetId")
    private Long id;
    private String firstName;
    private String lastName;

    //Constructor
    public Vet(Long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Vet(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Vet() {

    }

    //Getters and Setters
    public Long getIdVet() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setIdVet(Long idVet) {
        this.id = idVet;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
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

        if (!Objects.equals(id, vet.id)) return false;
        if (!Objects.equals(firstName, vet.firstName)) return false;
        return Objects.equals(lastName, vet.lastName);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        return result;
    }

    //toString
    @Override
    public String toString() {
        return "Vet{" +
                "idVet=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
