package com.practice.petclinicspringapplication.repository;

import com.practice.petclinicspringapplication.model.Owner;
import com.practice.petclinicspringapplication.model.Pet;
import com.practice.petclinicspringapplication.model.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OwnerRepo extends JpaRepository<Owner, Long> {
    @Query("SELECT p FROM Pet p WHERE p.owner = :owner")
    List<Pet> ownerPets(Owner owner);
    @Query("SELECT v FROM Visit v JOIN Pet p ON v.pet = p WHERE p.owner= :owner")
    List<Visit> ownerVisits(@Param("owner") Owner owner);
}
