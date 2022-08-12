package com.practice.petclinicspringapplication.repository;

import com.practice.petclinicspringapplication.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetRepo extends JpaRepository<Pet, Long> {
}
