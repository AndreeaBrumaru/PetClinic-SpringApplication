package com.practice.petclinicspringapplication.repositories;

import com.guru.sftpetclinic.model.Pet;
import org.springframework.data.repository.CrudRepository;

public interface PetRepo extends CrudRepository <Pet, Long> {
}
