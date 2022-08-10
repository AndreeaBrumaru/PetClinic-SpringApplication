package com.practice.petclinicspringapplication.repository;

import com.practice.petclinicspringapplication.model.Visit;
import org.springframework.data.repository.CrudRepository;

public interface VisitRepo extends CrudRepository<Visit, Long> {
}
