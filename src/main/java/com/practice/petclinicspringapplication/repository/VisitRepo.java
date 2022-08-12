package com.practice.petclinicspringapplication.repository;

import com.practice.petclinicspringapplication.model.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitRepo extends JpaRepository<Visit, Long> {
}
