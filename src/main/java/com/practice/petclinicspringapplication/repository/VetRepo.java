package com.practice.petclinicspringapplication.repository;

import com.practice.petclinicspringapplication.model.Vet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VetRepo extends JpaRepository<Vet, Long> {
}
