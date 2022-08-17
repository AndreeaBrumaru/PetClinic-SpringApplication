package com.practice.petclinicspringapplication.dto;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

public class OwnerDto {
    private Long id;
    @NotBlank(message="First name is mandatory")
    private String firstName;
    @NotBlank(message="Last name is mandatory")
    private String lastName;

    //Constructors
    public OwnerDto() {
    }

    public OwnerDto(Long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    //getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

        OwnerDto ownerDto = (OwnerDto) o;

        if (!Objects.equals(id, ownerDto.id)) return false;
        if (!Objects.equals(firstName, ownerDto.firstName)) return false;
        return Objects.equals(lastName, ownerDto.lastName);
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
        return "OwnerDto{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
