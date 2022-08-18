package com.practice.petclinicspringapplication.dto;

import com.practice.petclinicspringapplication.model.Pet;
import com.practice.petclinicspringapplication.model.Vet;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.Objects;

public class VisitDto {
    private Long id;
    @NotBlank(message = "Reason of visit is mandatory.")
    private String reasonForVisit;
    private LocalDate dateOfVisit;
    private Pet pet;
    private Vet vet;

    //Constructors
    public VisitDto(Long id, String reasonForVisit, Pet pet, Vet vet) {
        this.id = id;
        this.reasonForVisit = reasonForVisit;
        this.pet = pet;
        this.vet = vet;
    }

    public VisitDto(Long id, String reasonForVisit, LocalDate dateOfVisit) {
        this.id = id;
        this.reasonForVisit = reasonForVisit;
        this.dateOfVisit = dateOfVisit;
    }

    public VisitDto() {
    }

    public VisitDto(Long id, String reasonForVisit, LocalDate dateOfVisit, Pet pet, Vet vet) {
        this.id = id;
        this.reasonForVisit = reasonForVisit;
        this.dateOfVisit = dateOfVisit;
        this.pet = pet;
        this.vet = vet;
    }

    //getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

        VisitDto visitDto = (VisitDto) o;

        if (!Objects.equals(id, visitDto.id)) return false;
        if (!Objects.equals(reasonForVisit, visitDto.reasonForVisit))
            return false;
        return Objects.equals(dateOfVisit, visitDto.dateOfVisit);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (reasonForVisit != null ? reasonForVisit.hashCode() : 0);
        result = 31 * result + (dateOfVisit != null ? dateOfVisit.hashCode() : 0);
        return result;
    }

    //toString
    @Override
    public String toString() {
        return "VisitDto{" +
                "id=" + id +
                ", reasonForVisit='" + reasonForVisit + '\'' +
                ", dateOfVisit=" + dateOfVisit +
                '}';
    }
}
