package com.practice.petclinicspringapplication.service;

import com.practice.petclinicspringapplication.model.Visit;

import java.time.LocalDate;
import java.util.List;

public interface IVisitService {
    void add(Visit visit);
    Visit findById(Long idVisit);
    List<Visit> findAll();
    Long count();
    void update(Long id, String reason, LocalDate date, Long owner_id, Long pet_id, Long vet_id);
    void deleteById(Long idVisit);
}
