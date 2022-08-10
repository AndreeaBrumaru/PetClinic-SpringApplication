package com.practice.petclinicspringapplication.repository;

import com.practice.petclinicspringapplication.model.Owner;
import org.springframework.data.repository.CrudRepository;

public interface OwnerRepo extends CrudRepository<Owner, Long> {
}
