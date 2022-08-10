package com.practice.petclinicspringapplication.repository;

import com.practice.petclinicspringapplication.model.Visit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitRepo extends CrudRepository<Visit, Long> {
}
