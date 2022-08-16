package com.practice.petclinicspringapplication.repository;

import com.practice.petclinicspringapplication.model.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VisitRepo extends JpaRepository<Visit, Long> {

    List<Visit> findByVetId(Long vetId);
    List<Visit> findByPetId(Long petId);
}
