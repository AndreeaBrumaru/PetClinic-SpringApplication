package com.practice.petclinicspringapplication.model;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
public class Owner{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idOwner;
    private String firstName;
    private String lastName;
    @OneToMany(mappedBy = "owner")
    private Set<Pet> pets;
    @OneToMany(mappedBy = "owner")
    private Set<Visit> visits;

    //Constructor
    public Owner(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Owner() {

    }

    //Getters and setters
    public Long getIdOwner() {
        return idOwner;
    }

    public void setIdOwner(Long idOwner) {
        this.idOwner = idOwner;
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

    public Set<Pet> getPets() {
        return pets;
    }

    public void setPets(Set<Pet> pets) {
        this.pets = pets;
    }

    public Set<Visit> getVisits() {
        return visits;
    }

    public void setVisits(Set<Visit> visits) {
        this.visits = visits;
    }

    //Equals and hashcode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Owner owner = (Owner) o;

        if (!Objects.equals(idOwner, owner.idOwner)) return false;
        if (!Objects.equals(firstName, owner.firstName)) return false;
        return Objects.equals(lastName, owner.lastName);
    }

    @Override
    public int hashCode() {
        int result = idOwner != null ? idOwner.hashCode() : 0;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        return result;
    }

    //toString
    @Override
    public String toString() {
        return "Owner{" +
                "idOwner=" + idOwner +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
