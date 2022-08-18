package com.practice.petclinicspringapplication.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "owners")
public class Owner{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ownerId")
    private Long id;
    private String firstName;
    private String lastName;

    //Constructor
    public Owner(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Owner(Long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Owner() {

    }

    //Getters and setters
    public Long getIdOwner() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setIdOwner(Long idOwner) {
        this.id = idOwner;
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

        Owner owner = (Owner) o;

        if (!Objects.equals(id, owner.id)) return false;
        if (!Objects.equals(firstName, owner.firstName)) return false;
        return Objects.equals(lastName, owner.lastName);
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
        return "Owner{" +
                "idOwner=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
