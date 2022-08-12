package com.practice.petclinicspringapplication.repository;

import com.practice.petclinicspringapplication.model.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerRepo extends JpaRepository<Owner, Long> {
}
