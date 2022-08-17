package com.practice.petclinicspringapplication.repository;

import com.practice.petclinicspringapplication.model.Pet;
import com.practice.petclinicspringapplication.model.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepo extends JpaRepository<Pet, Long> {

    List<Pet> findByOwnerId(Long ownerId);
    @Query("SELECT v FROM Visit v WHERE v.pet = :pet")
    List<Visit> getVisits(Pet pet);
}
