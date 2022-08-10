package com.practice.petclinicspringapplication.repository;

import com.practice.petclinicspringapplication.model.Vet;
import org.springframework.data.repository.CrudRepository;

public interface VetRepo extends CrudRepository<Vet, Long> {
}
