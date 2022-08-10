package com.practice.petclinicspringapplication.repository;

import com.practice.petclinicspringapplication.model.Pet;
import org.springframework.data.repository.CrudRepository;

public interface PetRepo extends CrudRepository<Pet, Long> {
}
