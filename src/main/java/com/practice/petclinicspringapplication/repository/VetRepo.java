package com.practice.petclinicspringapplication.repository;

import com.practice.petclinicspringapplication.model.Pet;
import com.practice.petclinicspringapplication.model.Vet;
import com.practice.petclinicspringapplication.model.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface VetRepo extends JpaRepository<Vet, Long> {
    @Query("SELECT v FROM Visit v WHERE v.vet = :vet")
    List<Visit> getVetsVisits(Vet vet);

    @Query("SELECT p FROM Pet p JOIN Visit v ON v.pet = p WHERE v.vet = :vet")
    List<Pet> getVetsPets(Vet vet);
}
